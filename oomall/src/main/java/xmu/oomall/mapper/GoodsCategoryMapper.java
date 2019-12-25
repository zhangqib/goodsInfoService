package xmu.oomall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.oomall.domain.po.GoodsCategoryPo;

import java.util.List;

/**
 * @author zhang
 */
@Component
@Mapper
public interface GoodsCategoryMapper {
    /**
     * 删除一个类别
     *
     * @param id 类别id
     * @return 删除的行数
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    /**
     * 插入一个类别
     *
     * @param record 待插入的类别
     * @return 生成的id
     */
    int insert(@Param(value = "goodsCategory") GoodsCategoryPo record);

    /**
     * 通过主码查询类别
     *
     * @param id 品牌id
     * @return 查询出来的品牌的对象
     */
    GoodsCategoryPo selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询全部分类
     *
     * @return 品牌列表
     */
    List<GoodsCategoryPo> selectAll();

    /**
     * 查询一级分类
     *
     * @return 一级分类列表
     */
    List<GoodsCategoryPo> selectOneLevelGoodsCategories();

    /**
     * 根据父id查询他的子分类
     *
     * @param pid 父类别id
     * @return pid对应分类的子分类列表
     */
    List<GoodsCategoryPo> selectSecondLevelGoodsCategories(@Param("pid") Integer pid);

    /**
     * 更新一个品牌
     *
     * @param record 待更新的品牌
     * @return 更新的行数
     */
    int updateByPrimaryKey(@Param("goodsCategory") GoodsCategoryPo record);
}