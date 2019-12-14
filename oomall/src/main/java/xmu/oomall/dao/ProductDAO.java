package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Product;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;
import xmu.oomall.util.Copyer;

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
     * @param id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        return productMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 根据id查找product
     *
     * @param id
     * @return product
     */
    public Product selectById(Integer id) {
        return (Product) productMapper.selectByPrimaryKey(id);
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
        return products(productMapper.selectByGoodsId(id));
    }

    /**
     * 根据productId修改product
     *
     * @param product
     * @return 更新是否成功
     */
    public Product updateById(Product product) {
        if (isArgsInvalid(product)) {
            return null;
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
        if (!Copyer.Copy(product, productPo)) {
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
}
