package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Product;
import xmu.oomall.domain.po.ProductPo;
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
     *
     * @param product
     * @return 更新完id的product
     */
    public Product insert(Product product) {
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
     * 根据productId修改product
     *
     * @param product
     * @return 更新是否成功
     */
    public boolean updateById(Product product) {
        return productMapper.updateByPrimaryKey(product) == 1;
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
        return productMapper.selectByGoodsId(id);
    }

    public boolean updateById(ProductPo productPo) {
        return productMapper.updateByPrimaryKey(productPo) == 1;
    }

    public boolean insert(ProductPo productPo) {
        return productMapper.insert(productPo) == 1;
    }
}
