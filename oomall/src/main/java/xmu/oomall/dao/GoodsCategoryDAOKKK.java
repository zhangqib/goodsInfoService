package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.GoodsCategory;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.mapper.GoodsCategoryMapper;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;
import xmu.oomall.util.Copyer;

import java.util.ArrayList;
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

    public Integer deleteById(Integer id) {
        return -1;
    }
}
