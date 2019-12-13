package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.GoodsCategory;
import xmu.oomall.domain.po.GoodsCategoryPo;
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
     *
     * @param goods
     * @return 更新完id的商品
     */
    public GoodsCategory insert(GoodsCategory goodsCategory) {
        return null;
    }

    public boolean deleteById(Integer id) {
        return false;
    }

    /**
     * 根据id返回对应的商品
     *
     * @param id
     * @return Goods
     */
    public GoodsCategory selectById(Integer id) {
        return null;
    }

    /**
     * /**
     * 根据参数的id更新对应的goods
     *
     * @param goods
     * @return 更新是否成功
     */
    public boolean updateById(GoodsCategory goodsCategory) {
        return true;
    }


    public List<GoodsCategory> selectOneLevelGoodsCategories(Integer page, Integer limit) {
        return null;
    }

    public GoodsCategory selectSecondLevelGoodsCategories(Integer id, Integer page, Integer limit) {
        return null;
    }

    public List<GoodsCategory> selectGoodsCategoriesByCondition(Integer page, Integer limit) {
        return null;
    }

    public boolean updateById(GoodsCategoryPo goodsCategoryPo) {
        return false;
    }

    public GoodsCategoryPo insert(GoodsCategoryPo goodsCategoryPo) {
        return null;
    }
}
