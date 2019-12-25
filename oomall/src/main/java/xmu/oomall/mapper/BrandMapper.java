package xmu.oomall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.oomall.domain.po.BrandPo;

import java.util.List;

/**
 * @author zhang
 */
@Component
@Mapper
public interface BrandMapper {
    /**
     * 通过主码删除品牌
     *
     * @param id 品牌id
     * @return 删除的行数（成功为1, 失败为0, 其他情况可能是数据库出错了)
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    /**
     * 插入一个品牌
     *
     * @param record 待插入的对象
     * @return id
     */
    int insert(@Param("brand") BrandPo record);


    /**
     * 通过主码查询品牌
     *
     * @param id 品牌id
     * @return 品牌对象
     */
    BrandPo selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询所有品牌
     *
     * @return 品牌列表
     */
    List<BrandPo> selectAll();

    /**
     * 条件查询
     *
     * @param id brandId
     * @param name brandName
     * @return 品牌类表
     */
    List<BrandPo> selectByCondition(@Param("id") Integer id, @Param("name") String name);

    /**
     * 通过id更新一个品牌
     *
     * @param record 包含着待更新属性的品牌对象
     * @return 更新的行数
     */
    int updateByPrimaryKey(@Param("brand") BrandPo record);
}