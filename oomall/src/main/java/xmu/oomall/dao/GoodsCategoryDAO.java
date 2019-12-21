package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.mapper.GoodsCategoryMapper;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;

import java.util.List;

/**
 * @author zhang
 */
@Repository
public class GoodsCategoryDAO {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 将商品插入数据库
     * @param goodsCategory
     * @return 更新完id的商品
     */
    public GoodsCategoryPo insert(GoodsCategoryPo goodsCategory) {
        if (isArgsInvalid(goodsCategory)) {
            return null;
        }
        goodsCategoryMapper.insert(goodsCategory);
        return goodsCategory;
    }

    /**
     * 删除分类及其所有子分类, 并将所有属于被删除分类的商品的分类设为空
     * @param id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        List<GoodsCategoryPo> subGoodsCategories = selectSecondLevelGoodsCategories(id);
        subGoodsCategories.forEach(subGoodsCategory -> {
            goodsMapper.cleanCategory(subGoodsCategory.getId());
            goodsCategoryMapper.deleteByPrimaryKey(subGoodsCategory.getId());
        });
        goodsMapper.cleanCategory(id);
        return goodsCategoryMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 根据id返回对应的商品
     *
     * @param id
     * @return Goods
     */
    public GoodsCategoryPo selectById(Integer id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 跟新分类
     * @param goodsCategory
     * @return 更新是否成功
     */
    public GoodsCategoryPo updateById(GoodsCategoryPo goodsCategory) {
        if (isArgsInvalid(goodsCategory)) {
            return null;
        }
        if (goodsCategoryMapper.updateByPrimaryKey(goodsCategory) == 0) {
            return null;
        }
        return goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getId());
    }

    /**
     * 查询一级分类
     * @param page
     * @param limit
     * @return 一级分类列表
     */
    public List<GoodsCategoryPo> selectOneLevelGoodsCategories(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsCategoryMapper.selectOneLevelGoodsCategories();
    }

    /**
     * 查询pid分类下的子分类
     * @param pid
     * @param page
     * @param limit
     * @return 子分类列表
     */
    public List<GoodsCategoryPo> selectSecondLevelGoodsCategories(Integer pid, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsCategoryMapper.selectSecondLevelGoodsCategories(pid);
    }

    /**
     * 分页返回类别列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<GoodsCategoryPo> selectGoodsCategoriesByCondition(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsCategoryMapper.selectAll();
    }

    /**
     * 查询pid分类下的所有子分类
     * @param pid
     * @return 子分类列表
     */
    private List<GoodsCategoryPo> selectSecondLevelGoodsCategories(Integer pid) {
        return goodsCategoryMapper.selectSecondLevelGoodsCategories(pid);
    }

    private boolean isArgsInvalid(GoodsCategoryPo goodsCategory) {
        if (goodsCategory.getBeDeleted()) {
            return true;
        }
        Integer pid = goodsCategory.getPid();
        return pid != null && goodsCategoryMapper.selectByPrimaryKey(pid) == null;
    }

    public GoodsCategoryPo updatePidById(GoodsCategoryPo goodsCategory) {
        if (goodsCategoryMapper.updateByPrimaryKey(goodsCategory) == 1) {
            return goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getId());
        }
        return null;
    }
}
