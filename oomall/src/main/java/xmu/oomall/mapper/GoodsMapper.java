package xmu.oomall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.oomall.domain.po.GoodsPo;

import java.util.List;

/**
 * @author zhang
 */
@Component
@Mapper
public interface GoodsMapper {
    /**
     * 删除一个goods
     *
     * @param id goodsId
     * @return 删除的行数
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    /**
     * 插入一个goods
     *
     * @param record 待插入的对象
     * @return 生成的id
     */
    int insert(@Param("goods") GoodsPo record);

    /**
     * 通过主码查询goods
     *
     * @param id goodsId
     * @return goods
     */
    GoodsPo selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 通过类别查询
     *
     * @param categoryId 类别id
     * @return 对应的商品列表
     */
    List<GoodsPo> selectByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 通过类别查询未下架的商品
     *
     * @param categoryId 类别id
     * @return 商品列表
     */
    List<GoodsPo> selectForSaleByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 通过品牌id查询商品
     *
     * @param  brandId 品牌id
     * @return 商品列表
     */
    List<GoodsPo> selectByBrandId(@Param("brandId") Integer brandId);

    /**
     * 通过品牌id查询未下架商品
     *
     * @param brandId 品牌id
     * @return 未下架商品列表
     */
    List<GoodsPo> selectForSaleByBrandId(@Param("brandId") Integer brandId);

    /**
     * 条件查询
     *
     * @param goodsSn 商品编号
     * @param name 商品名
     * @param statusCode 商品状态
     * @return goodsPo列表
     */
    List<GoodsPo> selectByCondition(@Param("goodsSn") String goodsSn, @Param("name") String name, @Param("statusCode") Integer statusCode);

    /**
     * 条件查询（只返回未下架商品）
     *
     * @param goodsSn 商品编号
     * @param name    商品名
     * @return 商品列表
     */
    List<GoodsPo> selectForSaleByCondition(@Param("goodsSn") String goodsSn, @Param("name") String name);

    /**
     * 通过主码更新对象
     *
     * @param record 要更新的对象
     * @return 更新的行数
     */
    int updateByPrimaryKey(@Param("goods") GoodsPo record);

    /**
     * 将商品品牌brandId的商品的品牌置为空值
     *
     * @param brandId 品牌id
     * @return 更新的商品数量
     */
    int cleanBrand(@Param("brandId") Integer brandId);

    /**
     * 将类别为categoryId的商品类别置为空值
     *
     * @param categoryId 类别id
     * @return 更新的商品数量
     */
    int cleanCategory(@Param("categoryId") Integer categoryId);
}