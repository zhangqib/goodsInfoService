package xmu.oomall.service;

import org.springframework.stereotype.Service;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.*;

import java.util.List;


/**
 * @Author Ke
 * @Description: GoodsServiceInterface
 * @create 2019/12/18 22:22
 */


@Service
public interface GoodsInfoService {
    //-----------------Goods---------------Goods-----------Goods---------

    /**
     * 1.管理员根据id搜索商品
     *
     * @param id：Integer
     * @return GoodsPo(可获取下架商品)
     */
    GoodsPo getGoodsById(Integer id);

    /**
     * 2.管理员根据条件搜索商品
     *
     * @param goodsSn:String   商品的序列号
     * @param goodsName:String 商品的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(可获取下架商品)
     */
    List<GoodsPo> listGoodsByCondition(String goodsSn, String goodsName, Integer page, Integer limit);

    /**
     * 3.管理员新建商品
     *
     * @param goods：GoodsPo
     * @return GoodsPo，新建的商品
     */
    GoodsPo addGoods(GoodsPo goods);

    /**
     * 4.管理员根据id修改商品
     *
     * @param goodsOld
     * @param goodsNew
     * @return GoodsPo，修改后的商品
     */
    GoodsPo updateGoodsById(GoodsPo goodsOld, GoodsPo goodsNew);

    /**
     * 5.管理员根据id删除商品
     *
     * @param goods
     * @return Integer
     */
    Integer deleteGoodsById(GoodsPo goods);

    /**
     * 1.用户根据id搜索商品
     *
     * @param userId：Integer
     * @param id：Integer
     * @return GoodsPo（不可获取下架商品）
     */
    GoodsPo getGoodsForSaleById(Integer userId,Integer id);

    /**
     * 2.用户根据条件搜素商品
     *
     * @param goodsName:String 商品的名字
     * @param page:Integer     第几页
     * @param limit:Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(不可获取下架商品)
     */
    List<GoodsPo> listGoodsForSaleByCondition(String goodsName, Integer page, Integer limit);


    /**
     * 3.用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id:Integer
     * @param page:Integer  第几页
     * @param limit:Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    List<GoodsPo> listGoodsForSaleByCategoryId(Integer id, Integer page, Integer limit);

    /**
     * 4.用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id:Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    List<GoodsPo> listGoodsForSaleByBrandId(Integer id, Integer page, Integer limit);

    /**
     * 1.判断商品是否在售（内部接口）
     *
     * @param id：Integer
     * @return boolean
     */
    boolean isGoodsOnSale(Integer id);

    /**
     * 2.根据id搜索商品(内部接口)
     *
     * @param id：Integer
     * @return GoodsPo（不可获取下架商品）
     */
    GoodsPo getGoodsInnerById(Integer id);

    //-----------------Product-------------Product-------------Product--------

    /**
     * 1.管理员搜索某个商品下的所有产品
     *
     * @param id:Integer
     * @param page:Integer  第几页
     * @param limit:Integer 一页多少
     * @return List<ProductPo>，所属该商品的产品列表
     */
    List<ProductPo> listProductsByGoodsId(Integer id, Integer page, Integer limit);


    /**
     * 2.管理员新建某个商品下的产品
     *
     * @param product:ProductPo
     * @return ProductPo，新建的商品
     */
    ProductPo addProduct(ProductPo product);

    /**
     * 3.管理员根据id修改产品
     *
     * @param product:ProductPo
     * @return ProductPo，修改后的产品
     */
    ProductPo updateProductById(ProductPo product);

    /**
     * 4.管理员根据id删除产品
     *
     * @param product:ProductPo
     * @return boolean
     */
    boolean deleteProductById(ProductPo product);

    /**
     * 1.用户根据id搜索产品（内部接口）
     *
     * @param id:Integer
     * @return ProductPo，搜索到的产品
     */
    ProductPo getProductById(Integer id);

    /**
     * 2.根据id修改Product的库存（内部接口）
     *
     * @param orderItemList：List<OrderItem>(根据orderItemList来修改库存
     * @param operation:boolean(true加库存，false减库存
     * @return boolean ，修改成功与否
     */
    boolean updateStockByProductId(List<OrderItem> orderItemList, boolean operation);

    //-----------------Brand---------------Brand-----------Brand---------

    /**
     * 1.管理员根据id搜索品牌
     *
     * @param id:Integer
     * @return BrandPo
     */
    BrandPo getBrandById(Integer id);

    /**
     * 2.管理员根据条件搜索品牌
     *
     * @param brandId:String   品牌的id
     * @param brandName:String 品牌的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    List<BrandPo> listBrandsByCondition(Integer brandId, String brandName, Integer page, Integer limit);

    /**
     * 3.管理员创建品牌
     *
     * @param brand:BrandPo 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    BrandPo addBrand(BrandPo brand);

    /**
     * 4.管理员修改品牌
     *
     * @param brand：BrandPo)(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    BrandPo updateBrandById(BrandPo brand);

    /**
     * 5.管理员根据id删除品牌
     *
     * @param id：Integer
     * @return boolean
     */
    boolean deleteBrandById(Integer id);

    /**
     * 1.用户根据id搜索品牌
     *
     * @param id:Integer
     * @return BrandPo
     */
    BrandPo getBrandForUserById(Integer id);

    /**
     * 2.用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    List<BrandPo> listBrandsByCondition(Integer page, Integer limit);

    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------

    /**
     * 1.管理员根据id搜索分类
     *
     * @param id：Integer
     * @return GoodsCategoryPo
     */
    GoodsCategoryPo getGoodsCategoryById(Integer id);

    /**
     * 2.管理员搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    List<GoodsCategoryPo> listGoodsCategories(Integer page, Integer limit);

    /**
     * 3.管理员新建分类
     *
     * @param goodsCategory：GoodsCategoryPo(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    GoodsCategoryPo addGoodsCategory(GoodsCategoryPo goodsCategory);

    /**
     * 4.管理员修改分类
     *
     * @param goodsCategory：GoodsCategoryPo(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    GoodsCategoryPo updateGoodsCategoryById(GoodsCategoryPo goodsCategory);

    /**
     * 5.管理员修改子分类在父分类下的位置
     *
     * @param goodsCategory：GoodsCategoryPo(body包含pid(只能修改pid不为空的项，且修改后pid不可为空))
     * @return GoodsCategoryPo
     */
    GoodsCategoryPo updateGoodsCategoryPidById(GoodsCategoryPo goodsCategory);

    /**
     * 6.管理员删除分类
     *
     * @param id：Integer
     * @return boolean
     */
    boolean deleteGoodsCategoryById(Integer id);

    /**
     * 1.用户搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    List<GoodsCategoryPo> listOneLevelGoodsCategories(Integer page, Integer limit);

    /**
     * 2.用户搜索某一级分类下的所有二级分类
     *
     * @param id：Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    List<GoodsCategoryPo> listSecondLevelGoodsCategoryById(Integer id, Integer page, Integer limit);
}
