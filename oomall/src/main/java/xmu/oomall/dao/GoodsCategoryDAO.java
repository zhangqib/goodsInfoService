package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.Product;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;

import java.util.List;

/**
 * @author zhang
 */
@Repository
public class GoodsCategoryDAO {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 将商品插入数据库
     * @param goods
     * @return 更新完id的商品
     */
    public GoodsCategory insert(GoodsCategory goodsCategory) {
    }

    /**
     * 根据id删除Goods，级联删除其对应的product
     * @param id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        List<Product> products = productMapper.selectByGoodsId(id);
        for(Product product: products) {
            if (productMapper.deleteByPrimaryKey(product.getId()) == 0) {
                return false;
            }
        }
        return goodsMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 根据id返回对应的商品
     * @param id
     * @return Goods
     */
    public GoodsCategory selectById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**

    /**
     * 根据参数的id更新对应的goods
     * @param goods
     * @return 更新是否成功
     */
    public GoodsCategory updateById(GoodsCategory goodsCategory) {
        return goodsMapper.updateByPrimaryKey(goods) == 1;
    }
}
