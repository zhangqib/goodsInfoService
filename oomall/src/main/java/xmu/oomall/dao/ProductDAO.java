package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.IRedisService;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;
import xmu.oomall.util.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang
 */
@Repository
public class ProductDAO {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private IRedisService iRedisService;

    @Autowired
    private Config config;

    /**
     * 插入一个product
     *
     * @param product
     * @return 成功：更新完id的product 失败： null
     */
    public ProductPo insert(ProductPo product) {
        if (isArgsInvalid(product)) {
            return null;
        }
        productMapper.insert(product);
        return product;
    }

    /**
     * 根据productId删除对应的product
     *
     * @param product 要删除的对象
     * @return 删除是否成功
     */
    public boolean deleteById(ProductPo product) {
        if (product.getBeDeleted()) {
            return false;
        }
        iRedisService.remove(product.gemRedisKey());
        iRedisService.remove(product.gemGoodsRedisKey());
        iRedisService.remove(GoodsPo.gemProductRedisKeys(product.getGoodsId()));
        return productMapper.deleteByPrimaryKey(product.getId()) == 1;
    }

    public boolean deleteById(Integer id) {
        return true;
    }

    /**
     * 根据id查找product
     *
     * @param id
     * @return product
     */
    public ProductPo selectById(Integer id) {
        ProductPo product = (ProductPo) iRedisService.get(ProductPo.gemRedisKey(id));
        if (product == null) {
            product =  productMapper.selectByPrimaryKey(id);
            if (product != null) {
                iRedisService.set(product.gemRedisKey(), product);
            }
        }
        return product;
    }

    /**
     * 通过goods查找它的所有product
     * @param id
     * @param page
     * @param limit
     * @return product列表
     */
    public List<ProductPo> selectByGoodsId(Integer id, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ProductPo> productPos = new ArrayList<>();

        List<String> productIds = iRedisService.sget(GoodsPo.gemProductRedisKeys(id));
        if (productIds.isEmpty()) {
            productPos = productMapper.selectByGoodsId(id);
            if (productPos.size() == 0) {
                return productPos;
            }
            for (ProductPo productPo: productPos) {
                productIds.add(productPo.getId().toString());
            }
            iRedisService.sadd(GoodsPo.gemProductRedisKeys(id), productIds);
        } else {
            for (String productId: productIds) {
                productPos.add(selectById(Integer.valueOf(productId)));
            }
        }
        return productPos;
    }

    /**
     * 根据productId修改product
     *
     * @param product
     * @return 更新是否成功
     */
    public ProductPo updateById(ProductPo product) {
        if (product.getBeDeleted()) {
            return null;
        }
        Integer goodsId = product.getGoodsId();
        if(goodsId != null) {
             GoodsPo goods = goodsMapper.selectByPrimaryKey(goodsId);
             if (goods == null || goods.getBeDeleted()) {
                 return null;
             }
        }
        if (productMapper.updateByPrimaryKey(product) == 0) {
            return null;
        }
        iRedisService.remove(product.gemRedisKey());
        return productMapper.selectByPrimaryKey(product.getId());
    }

    boolean isArgsInvalid(ProductPo product) {
        if (product.getBeDeleted()) {
            return true;
        }
        Integer goodsId = product.getGoodsId();
        return goodsId != null && goodsMapper.selectByPrimaryKey(goodsId) == null;
    }

    public Boolean descStock(Integer productId, int dStock) {
        Integer redisStock = (Integer) iRedisService.get(ProductPo.gemStockRedisKey(productId));
        if (redisStock == null) {
            if (iRedisService.exists(ProductPo.gemStockRedisKey(productId))) {
                return false;
            } else {
                redisStock = preDescStock(productId);
                if (redisStock == 0) {
                    return false;
                }
            }
        }
        // redisStock 存在且大于0
        if (redisStock < dStock) {
            // 从数据库中扣除
            if (descStockFromDatabase(productId, dStock - redisStock)) {
                redisStock = 0;
            } else {
                return false;
            }
        } else {
            // 从redis中扣除
            redisStock -= dStock;
        }
        if (redisStock < config.getRedisStockThreshold()){
            redisStock += preDescStock(productId);
        }
        iRedisService.set(ProductPo.gemStockRedisKey(productId), redisStock);
        return true;
    }

    private boolean descStockFromDatabase(Integer productId, int dStock) {
        if (iRedisService.exists(ProductPo.gemStockRedisKey(productId))) {
            return false;
        }
        ProductPo product = productMapper.selectByPrimaryKey(productId);
        if (product == null || product.getBeDeleted()) {
            return false;
        } else if (product.getSafetyStock() < dStock && product.getSafetyStock() != 0){
            return false;
        } else if (product.getSafetyStock() == 0) {
            iRedisService.set(product.gemRedisStockUnDescable(), 1);
            return false;
        } else {
            product.setSafetyStock(product.getSafetyStock() - dStock);
            return productMapper.updateByPrimaryKey(product) == 1;
        }
    }

    private Integer preDescStock(Integer productId) {
        if (iRedisService.exists(ProductPo.gemRedisStockUnDescable(productId))) {
            return 0;
        } else {
            // 可以扣库存
            ProductPo product = productMapper.selectByPrimaryKey(productId);
            if (product == null) {
                return 0;
            }
            Integer descQty;
            if (product.getSafetyStock() < config.getPreDescQty()) {
                descQty = product.getSafetyStock();
            } else {
                descQty = config.getPreDescQty();
            }
            product.setSafetyStock(product.getSafetyStock() - descQty);
            return productMapper.updateByPrimaryKey(product) == 1? descQty: 0;
        }
    }

    public Boolean incrStock(Integer productId, int dStock) {
        ProductPo product = selectById(productId);
        if (product == null || product.getBeDeleted()) {
            return false;
        } else {
            product.setSafetyStock(product.getSafetyStock() + dStock);
            if(productMapper.updateByPrimaryKey(product) == 1) {
                iRedisService.remove(product.gemRedisStockUnDescable());
                return true;
            } else {
                return false;
            }
        }
    }
}
