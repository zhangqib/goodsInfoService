package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.IRedisService;
import xmu.oomall.domain.Product;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;
import xmu.oomall.util.Copyer;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public Product insert(Product product) {
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
    public boolean deleteById(Product product) {
        if (product.getBeDeleted()) {
            return false;
        }
        iRedisService.remove(product.getRedisKey());
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
    public Product selectById(Integer id) {
        ProductPo product = (ProductPo) iRedisService.get(Product.getRedisKey(id));
        if (product == null) {
            product =  productMapper.selectByPrimaryKey(id);
            if (product != null) {
                iRedisService.set(product.getRedisKey(), product);
            }
        }
        return product(product);
    }

    /**
     * 通过goods查找它的所有product
     * @param id
     * @param page
     * @param limit
     * @return product列表
     */
    public List<Product> selectByGoodsId(Integer id, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<ProductPo> productPos = new ArrayList<>();
        
        List<String> productIds = iRedisService.sget(GoodsPo.getProductRedisKeys(id));
        if (productIds == null) {
            productPos = productMapper.selectByGoodsId(id);
            if (productPos.size() == 0) {
                return products(productPos);
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
        return products(productPos);
    }

    /**
     * 根据productId修改product
     *
     * @param product
     * @return 更新是否成功
     */
    public Product updateById(Product product) {
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
        return product(productMapper.selectByPrimaryKey(product.getId()));
    }

    private Product product(ProductPo productPo) {
       if (productPo == null) {
           return null;
       }
       Product product = new Product();
        if (!Copyer.Copy(productPo, product)) {
            return null;
        }
        return product;
    }
    private List<Product> products(List<ProductPo> productPos) {
        if (productPos == null) {
            return null;
        }
       List<Product> products = new LinkedList<>();
        for (ProductPo productPo : productPos) {
            products.add(product(productPo));
        }
        return products;
    }

    boolean isArgsInvalid(Product product) {
        if (product.getBeDeleted()) {
            return true;
        }
        Integer goodsId = product.getGoodsId();
        return goodsId != null && goodsMapper.selectByPrimaryKey(goodsId) == null;
    }

    public Boolean descStock(Integer productId, int dStock) {
        ProductPo product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return false;
        } else if (product.getSafetyStock() < dStock){
            return false;
        } else {
            product.setSafetyStock(product.getSafetyStock() - dStock);
            return productMapper.updateByPrimaryKey(product) == 1;
        }
    }
}
