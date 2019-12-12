package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Product;
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

    /**
     * 插入一个product
     * @param product
     * @return 更新完id的product
     */
    public Product insert(Product product) {
        productMapper.insert(product);
        return product;
    }

    /**
     * 根据productId删除对应的product
     * @param id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        return productMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 根据id查找product
     * @param id
     * @return product
     */
    public Product selectById(Integer id) {
//        return productMapper.selectByPrimaryKey(id);
        return new Product();
    }

    /**
     * 根据goodsId查找对应的product列表
     * @param id
     * @return products
     */
    public List<Product> selectByGoodsId(Integer id) {
//        return productMapper.selectByGoodsId(id);
        return new ArrayList<Product>();
    }

    /**
     * 根据productId修改product
     * @param product
     * @return 更新是否成功
     */
    public boolean updateById(Product product) {
        return productMapper.updateByPrimaryKey(product) == 1;
    }
}
