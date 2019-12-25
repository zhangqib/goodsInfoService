package xmu.oomall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.oomall.domain.po.ProductPo;

import java.util.List;

/**
 * @author zhang
 */
@Component
@Mapper
public interface ProductMapper {
    /**
     * 通过主码删除product
     *
     * @param id productId
     * @return 删除的行数
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    /**
     * 插入一个product
     *
     * @param record 待插入的product
     * @return 生成的id
     */
    int insert(@Param("product") ProductPo record);

    /**
     * 插入products列表
     *
     * @param products product列表
     * @return 插入的数量
     */
    int insertProducts(@Param("products") List<ProductPo> products);

    /**
     * 通过主码查询product
     *
     * @param id productId
     * @return product
     */
    ProductPo selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 根据商品id返回对应的product列表
     *
     * @param goodsId goodsId
     * @return products
     */
    List<ProductPo> selectByGoodsId(@Param("goodsId") Integer goodsId);


    /**
     * 更新product
     *
     * @param record productPo
     * @return 更新的行数
     */
    int updateByPrimaryKey(@Param("product") ProductPo record);

}
