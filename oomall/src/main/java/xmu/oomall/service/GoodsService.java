package xmu.oomall.service;

import xmu.oomall.domain.GoodsCategory;
import org.springframework.stereotype.Service;
import xmu.oomall.domain.*;

import java.util.List;

/**
 * @Author Ke
 * @Description: GoodsServiceInterface
 * @create 2019/12/15 18:22
 */


@Service
public interface GoodsService {
    //-----------------Goods---------------Goods-----------Goods---------

    /**
     * 管理员根据id搜索商品
     *
     * @param id：Integer
     * @return Goods(可获取下架商品)
     */
    Goods getGoodsById(Integer id);

    /**
     * 用户根据id搜索商品
     *
     * @param id：Integer
     * @return Goods（不可获取下架商品）
     */
    Goods getGoodsForSaleById(Integer id);

//    /**
//     * 管理员根据id搜索商品的特殊运费规则
//     *
//     * @param id：Integer
//     * @return SpecialFreight（可获取下架商品）
//     */
//    SpecialFreight getSpecialFreightsByGoodsId(Integer id);
//
//    /**
//     * 用户根据id搜索商品的特殊运费规则
//     *
//     * @param id：Integer
//     * @return SpecialFreight（不可获取下架商品）
//     */
//    SpecialFreight getSpecialFreightsByForSaleGoodsId(Integer id);

    /**
     * 管理员根据条件搜索商品
     *
     * @param goodsSn:String   商品的序列号
     * @param goodsName:String 商品的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<Goods>,搜索到的商品的列表(可获取下架商品)
     */
    List<Goods> listGoodsByCondition(String goodsSn,
                                     String goodsName,
                                     Integer page,
                                     Integer limit);

    /**
     * 用户根据条件搜素商品
     *
     * @param goodsName:String 商品的名字
     * @param page:Integer     第几页
     * @param limit:Integer    一页多少
     * @return List<Goods>,搜索到的商品的列表（不可获取下架商品）
     */
    List<Goods> listGoodsForSaleByCondition(String goodsName,
                                            Integer page,
                                            Integer limit);

    /**
     * 管理员根据商品分类id搜索该分类下的所有商品（看得到下架商品）
     *
     * @param id:Integer
     * @param page:Integer  第几页
     * @param limit:Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    List<Goods> listGoodsByCategoryId(Integer id,
                                      Integer page,
                                      Integer limit);

    /**
     * 用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id:Integer
     * @param page:Integer  第几页
     * @param limit:Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    List<Goods> listGoodsForSaleByCategoryId(Integer id,
                                             Integer page,
                                             Integer limit);

    /**
     * 管理员根据品牌id搜索该品牌下的所有商品（可以看到下架商品）
     *
     * @param id:Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    List<Goods> listGoodsByBrandId(Integer id,
                                   Integer page,
                                   Integer limit);

    /**
     * 用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id:Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    List<Goods> listGoodsForSaleByBrandId(Integer id,
                                          Integer page,
                                          Integer limit);

    /**
     * 管理员新建商品
     *
     * @param goods：Goods
     * @return Goods，新建的商品
     */
    Goods addGoods(Goods goods);

    /**
     * 管理员根据id修改商品
     *
     * @param goods:Goods
     * @return Goods，修改后的商品
     */
    Goods updateGoodsById(Goods goods);

    /**
     * 管理员根据id删除商品
     *
     * @param id：Integer
     * @return Integer,-1表示删除失败，0表示该商品仍在售，1表示删除成功
     */
    Integer deleteGoodsById(Integer id);

    /**
     * 内部接口————————————判断商品是否在售
     *
     * @param id：Integer
     * @return Boolean
     */
    Integer isGoodsOnSale(Integer id);
    //-----------------Goods---------------Goods-----------Goods---------
    //-----------------Goods---------------Goods-----------Goods---------

    //-----------------Product-------------Product-------------Product--------

    /**
     * 管理员根据id搜索产品
     *
     * @param id:Integer
     * @return Product，搜索到的产品
     */
    Product getProductById(Integer id);

    /**
     * 管理员搜索某个商品下的所有产品
     *
     * @param id:Integer
     * @param page:Integer  第几页
     * @param limit:Integer 一页多少
     * @return List<Product>，所属该商品的产品列表
     */
    List<Product> listProductsByGoodsId(Integer id,
                                        Integer page,
                                        Integer limit);

    /**
     * 管理员新建某个商品下的产品
     *
     * @param product:Product
     * @return Product，新建的商品
     */
    Product addProduct(Product product);

    /**
     * 管理员根据id修改产品
     *
     * @param product:Product
     * @return Product，修改后的产品
     */
    Product updateProductById(Product product);

    /**
     * 管理员根据id修改Product的库存
     *
     * @param id：Integer
     * @param quantity:Integer(RequestParam
     * @return Boolean ，修改成功与否
     */
    Boolean updateStockByProductId(Integer id, Integer quantity);

    /**
     * 管理员根据id删除产品
     *
     * @param id:Integer
     * @return Boolean
     */
    Boolean deleteProductById(Integer id);

    //-----------------Product-------------Product-------------Product--------
    //-----------------Product-------------Product-------------Product--------


    //-----------------Brand---------------Brand-----------Brand---------

    /**
     * 管理员或用户根据id搜索品牌
     *
     * @param id:Integer
     * @return Brand
     */
    Brand getBrandById(Integer id);

    /**
     * 管理员根据条件搜索品牌
     *
     * @param brandId:String   品牌的id
     * @param brandName:String 品牌的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<Brand>,搜索到的品牌列表
     */
    List<Brand> listBrandsByCondition(String brandId,
                                      String brandName,
                                      Integer page,
                                      Integer limit);

    /**
     * 用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<Brand>,搜索到的品牌列表
     */
    List<Brand> listBrandsByCondition(Integer page,
                                      Integer limit);

    /**
     * 管理员创建品牌
     *
     * @param brand:Brand 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return Brand
     */
    Brand addBrand(Brand brand);

    /**
     * 管理员修改品牌
     *
     * @param brand：Brand(body包含name、description、picURL(上传图片产生))
     * @return Brand
     */
    Brand updateBrandById(Brand brand);

    /**
     * 管理员根据id删除品牌
     *
     * @param id：Integer
     * @return Boolean
     */
    Boolean deleteBrandById(Integer id);

    //-----------------Brand---------------Brand-----------Brand---------
    //-----------------Brand---------------Brand-----------Brand---------

    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------

    /**
     * 管理员或用户根据id搜索分类
     *
     * @param id：Integer
     * @return GoodsCategory
     */
    GoodsCategory getGoodsCategoryById(Integer id);

    /**
     * 管理员或用户搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategory>
     */
    List<GoodsCategory> listGoodsCategories(Integer page,
                                            Integer limit);

    /**
     * 内部接口————————搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategory>
     */
    List<GoodsCategory> listOneLevelGoodsCategories(Integer page,
                                                    Integer limit);

    /**
     * 管理员或用户搜索某一级分类下的所有二级分类
     *
     * @param id：Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<GoodsCategory>
     */
    List<GoodsCategory> listSecondLevelGoodsCategoryById(Integer id,
                                                         Integer page,
                                                         Integer limit);

    /**
     * 管理员新建分类
     *
     * @param goodsCategory：GoodsCategory(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategory
     */
    GoodsCategory addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 管理员修改分类
     *
     * @param goodsCategory：GoodsCategory(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategory
     */
    GoodsCategory updateGoodsCategoryById(GoodsCategory goodsCategory);

    /**
     * 管理员删除分类
     *
     * @param id：Integer
     * @return Boolean
     */
    Boolean deleteGoodsCategoryById(Integer id);
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------

    /**
     * 上传图片
     *
     * @param
     * @return picUrl：String，上传图片的url
     */
    String uploadPic();
}
