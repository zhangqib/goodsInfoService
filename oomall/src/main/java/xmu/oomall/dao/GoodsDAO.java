package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;
import xmu.oomall.util.Copyer;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang
 */
@Repository
public class GoodsDAO {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 将商品插入数据库
     *
     * @param goods
     * @return 更新完id的商品
     */
    public GoodsPo insert(GoodsPo goods) {
        goodsMapper.insert(goods);
        return goods;
    }

    /**
     * 根据id删除Goods，级联删除其对应的product
     *
     * @param id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        List<ProductPo> products = productMapper.selectByGoodsId(id);
        for (ProductPo product : products) {
            if (productMapper.deleteByPrimaryKey(product.getId()) == 0) {
                return false;
            }
        }
        return goodsMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 根据id返回对应的商品
     *
     * @param id
     * @return Goods
     */
    public Goods selectById(Integer id) {
        GoodsPo goods = goodsMapper.selectByPrimaryKey(id);
        return goods(goods);
    }

    /**
     * 根据productId返回Goods
     *
     * @param productId
     * @return productId所属的商品
     */
    public Goods selectByProductId(Integer productId) {
        ProductPo product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return null;
        }
        GoodsPo goods = goodsMapper.selectByPrimaryKey(product.getGoodsId());
        return goods(goods);
    }

    /**
     * 根据参数的id更新对应的goods
     *
     * @param goods
     * @return 更新是否成功
     */
    public boolean updateById(GoodsPo goods) {
        return goodsMapper.updateByPrimaryKey(goods) == 1;
    }

    public List<GoodsPo> selectByCategoryId(Integer id, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<GoodsPo> goodsPos = goodsMapper.selectByCategoryId(id);
        return goodsPos;
    }

    public List<GoodsPo> selectByCondition(String goodsSn, String name, Integer statusCode, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        return goodsMapper.selectByCondition(goodsSn, name, statusCode);
    }

    /**
     * 将GoodsPo转换成Goods对象
     *
     * @param goodsPo
     * @return goods pojo
     */
    private Goods goods(GoodsPo goodsPo) {
        Goods goods = new Goods();
        return Copyer.Copy(goodsPo, goods) ? goods : null;
    }

    /**
     * 将goodsPo列表转换成goods类表
     *
     * @param goodsPoList
     * @return goodsList
     */
    private List<Goods> goodsList(List<GoodsPo> goodsPoList) {
        if (goodsPoList == null) {
            return null;
        }
        List<Goods> goodsList = new ArrayList<Goods>();
        for (GoodsPo goodsPo : goodsPoList) {
            goodsList.add(goods(goodsPo));
        }
        return goodsList;
    }
}
