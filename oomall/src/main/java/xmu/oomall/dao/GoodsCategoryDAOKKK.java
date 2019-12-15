package xmu.oomall.dao;

import org.springframework.stereotype.Repository;
import xmu.oomall.domain.GoodsCategory;

import java.util.List;

/**
 * @author zhang
 */
@Repository
public class GoodsCategoryDAOKKK {

    public GoodsCategory selectById(Integer id) {
        return null;
    }

    public List<GoodsCategory> selectGoodsCategoriesByCondition(Integer page, Integer limit) {
        return null;
    }

    public List<GoodsCategory> selectOneLevelGoodsCategories(Integer page, Integer limit) {
        return null;
    }

    public List<GoodsCategory> selectSecondLevelGoodsCategories(Integer id, Integer page, Integer limit) {
        return null;
    }

    public GoodsCategory insert(GoodsCategory goodsCategory) {
        return null;
    }

    public GoodsCategory updateById(GoodsCategory goodsCategory) {
        return null;
    }

    public Boolean deleteById(Integer id) {
        return true;
    }
}
