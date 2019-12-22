package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.IRedisService;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;

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
        iRedisService.remove(product.getRedisKey());
        iRedisService.remove(product.getGoodsRedisKey());
        iRedisService.remove(GoodsPo.getProductRedisKeys(product.getGoodsId()));
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
        ProductPo product = (ProductPo) iRedisService.get(ProductPo.getRedisKey(id));
        if (product == null) {
            product =  productMapper.selectByPrimaryKey(id);
            if (product != null) {
                iRedisService.set(product.getRedisKey(), product);
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

        List<String> productIds = iRedisService.sget(GoodsPo.getProductRedisKeys(id));
        if (productIds.isEmpty()) {
            productPos = productMapper.selectByGoodsId(id);
            if (productPos.size() == 0) {
                return productPos;
            }
            for (ProductPo productPo: productPos) {
                productIds.add(productPo.getId().toString());
            }
            iRedisService.sadd(GoodsPo.getProductRedisKeys(id), productIds);
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
        iRedisService.remove(product.getRedisKey());
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
        ProductPo product = productMapper.selectByPrimaryKey(productId);
        if (product == null || product.getBeDeleted()) {
            return false;
        } else if (product.getSafetyStock() < dStock){
            return false;
        } else {
            product.setSafetyStock(product.getSafetyStock() - dStock);
            return productMapper.updateByPrimaryKey(product) == 1;
        }
    }

    public Boolean incrStock(Integer productId, int dStock) {
        ProductPo product = selectById(productId);
        if (product == null || product.getBeDeleted()) {
            return false;
        } else {
            product.setSafetyStock(product.getSafetyStock() + dStock);
            return productMapper.updateByPrimaryKey(product) == 1;
        }
    }
}
