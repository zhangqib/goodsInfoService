package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.Product;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;

import java.util.ArrayList;

/**
 * @author zhang
 */
@Repository
public class GoodsDAO {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProductMapper productMapper;

    public Integer deleteByPrimaryKey(Integer id) {
        ArrayList<Product> products = productMapper.selectByGoodsId(id);
        for(Product product: products) {
            if (productMapper.deleteByPrimaryKey(product.getId()) == 0) {
                return 0;
            }
        }
        return goodsMapper.deleteByPrimaryKey(id);
    }

    public Goods selectByProductId(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        return goodsMapper.selectByPrimaryKey(product.getGoodsId());
    }

}
