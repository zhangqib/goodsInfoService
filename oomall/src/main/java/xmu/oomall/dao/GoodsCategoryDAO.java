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
    public GoodsCategory insert(GoodsCategory goodsCategory) {
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
    public GoodsCategory selectById(Integer id) {
        return (GoodsCategory) goodsCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 跟新分类
     * @param goodsCategory
     * @return 更新是否成功
     */
    public GoodsCategory updateById(GoodsCategory goodsCategory) {
        if (isArgsInvalid(goodsCategory)) {
            return null;
        }
        if (goodsCategoryMapper.updateByPrimaryKey(goodsCategory) == 0) {
            return null;
        }
        GoodsCategory newGoodsCategory = (GoodsCategory) goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getId());
        return newGoodsCategory;
    }

    /**
     * 查询一级分类
     * @param page
     * @param limit
     * @return 一级分类列表
     */
    public List<GoodsCategory> selectOneLevelGoodsCategories(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsCategories(goodsCategoryMapper.selectOneLevelGoodsCategories());
    }

    /**
     * 查询pid分类下的子分类
     * @param pid
     * @param page
     * @param limit
     * @return 子分类列表
     */
    public List<GoodsCategory> selectSecondLevelGoodsCategories(Integer pid, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsCategories(goodsCategoryMapper.selectSecondLevelGoodsCategories(pid));
    }

    /**
     * 分页返回类别列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<GoodsCategory> selectGoodsCategoriesByCondition(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsCategories(goodsCategoryMapper.selectAll());
    }

    /**
     * 查询pid分类下的所有子分类
     * @param pid
     * @return 子分类列表
     */
    private List<GoodsCategoryPo> selectSecondLevelGoodsCategories(Integer pid) {
        return goodsCategoryMapper.selectSecondLevelGoodsCategories(pid);
    }


    /**
     * PO -> POJO
     * @param goodsCategoryPo
     * @return POJO
     */
    private GoodsCategory goodsCategory(GoodsCategoryPo goodsCategoryPo) {
        GoodsCategory goodsCategory = new GoodsCategory();
        return Copyer.Copy(goodsCategoryPo, goodsCategory) ? goodsCategory: null;
    }

    /**
     * List<PO> -> List<POJO>
     * @param goodsCategoryPos
     * @return List<POJO>
     */
    private List<GoodsCategory> goodsCategories(List<GoodsCategoryPo> goodsCategoryPos) {
        if (goodsCategoryPos == null || goodsCategoryPos.isEmpty()) {
            return null;
        }
        List<GoodsCategory> goodsCategories = new ArrayList<GoodsCategory>();
        for (GoodsCategoryPo goodsCategoryPo: goodsCategoryPos) {
            goodsCategories.add(goodsCategory(goodsCategoryPo));
        }
        return goodsCategories;
    }

    private boolean isArgsInvalid(GoodsCategory goodsCategory) {
        if (goodsCategory.getBeDeleted()) {
            return true;
        }
        Integer pid = goodsCategory.getPid();
        return pid != null && goodsCategoryMapper.selectByPrimaryKey(pid) == null;
    }
}
