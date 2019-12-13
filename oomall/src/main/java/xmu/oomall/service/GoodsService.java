package xmu.oomall.service;

import org.springframework.stereotype.Service;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;

import java.util.List;


/**
 * @Author Ke
 * @Description: GoodsServiceInterface
 * @create 2019/12/12 22:22
 */


@Service
public interface GoodsService {
    //-----------------Goods---------------Goods-----------Goods---------

    /**
     * 管理员或用户根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods，搜索到的商品，此URL与WX端是同一个URL
     */
    public Object getGoodsById(Integer id);

    /**
     * 内部接口————————————判断商品是否在售
     *
     * @param id：Integer
     * @return Boolean
     */
    public Object isGoodsOnSale(Integer id);

    /**
     * 管理员根据条件搜索商品
     *
     * @param goodsSn:String   商品的序列号
     * @param goodsName:String 商品的名字
     * @param status:Integer   商品是否上架，这个域的取值以数据字典为准
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<GoodsPo>,搜索到的商品的列表
     */
    public Object listGoodsByCondition(String goodsSn,
                                       String goodsName,
                                       Integer status,
                                       Integer page,
                                       Integer limit);

    /**
     * 用户根据条件搜素商品
     *
     * @param goodsSn:String   商品的序列号
     * @param goodsName:String 商品的名字
     * @param page:Integer     第几页
     * @param limit:Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表
     */
    public Object listGoodsByCondition(String goodsSn,
                                       String goodsName,
                                       Integer page,
                                       Integer limit);

    /**
     * 用户根据商品分类搜索商品
     *
     * @param id:Integer(PathVariable
     * @param page:Integer            第几页
     * @param limit:Integer           一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    public Object ListGoodsByCategoryId(Integer id,
                                        Integer page,
                                        Integer limit);

    /**
     * 管理员新建商品
     *
     * @param goods：Goods(RequestBody
     * @return GoodsPo，新建的商品
     */
    public Object addGoods(Goods goods);

    /**
     * 管理员根据id修改商品
     *
     * @param id：Integer(PathVariable
     * @param goodsPo:GoodsPo(RequestBody
     * @return GoodsPo，修改后的商品
     */
    public Object updateGoodsById(Integer id,
                                  GoodsPo goodsPo);

    /**
     * 管理员根据id删除商品
     *
     * @param id：Integer(PathVariable
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    public Object deleteGoodsById(Integer id);
    //-----------------Goods---------------Goods-----------Goods---------
    //-----------------Goods---------------Goods-----------Goods---------

    //-----------------Product-------------Product-------------Product--------

    /**
     * 管理员根据id搜索产品
     *
     * @param id:Integer(PathVariable
     * @return Product，搜索到的产品
     */
    public Object getProductById(Integer id);

    /**
     * 管理员搜索某个商品下的所有产品
     *
     * @param id:Integer(PathVariable
     * @param page:Integer            第几页
     * @param limit:Integer           一页多少
     * @return List<ProductPo>，所属该商品的产品列表
     */
    public Object listProductsByGoodsId(Integer id,
                                        Integer page,
                                        Integer limit);

    /**
     * 管理员新建某个商品下的产品
     *
     * @param id:Integer(PathVariable
     * @param productPo:ProductPo(RequestBody
     * @return List<ProductPo>，属于这个商品的产品列表
     */
    public Object addProduct(Integer id,
                             ProductPo productPo);

    /**
     * 管理员根据id修改产品
     *
     * @param id:Integer(PathVariable
     * @param productPo:ProductPo(RequestBody
     * @return ProductPo，修改后的产品
     */
    public Object updateProductById(Integer id,
                                    ProductPo productPo);

    /**
     * 管理员根据id删除产品
     *
     * @param id:Integer(PathVariable
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    public Object deleteProductById(Integer id);

    //-----------------Product-------------Product-------------Product--------
    //-----------------Product-------------Product-------------Product--------


    //-----------------Brand---------------Brand-----------Brand---------

    /**
     * 管理员或用户根据id搜索品牌
     *
     * @param id:Integer(PathVariable
     * @return Brand
     */
    public Object getBrandById(Integer id);

    /**
     * 管理员根据条件搜索品牌
     *
     * @param brandId:String   品牌的id
     * @param brandName:String 品牌的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<brand>,搜索到的品牌列表
     */
    public Object listBrandsByCondition(String brandId,
                                        String brandName,
                                        Integer page,
                                        Integer limit);

    /**
     * 用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<brand>,搜索到的品牌列表
     */
    public Object listBrandsByCondition(Integer page,
                                        Integer limit);

    /**
     * 管理员创建品牌
     *
     * @param brand
     * @param brand:Brand 要添加的品牌
     * @return Brand
     */
    public Object addBrand(Brand brand);

    /**
     * 管理员修改品牌
     *
     * @param id：Integer（PathVariable
     * @param brand：Brand（RequestBody
     * @return Brand
     */
    public Object updateBrandById(Integer id, Brand brand);

    /**
     * 管理员根据id删除品牌
     *
     * @param id：Integer
     * @return Brand
     */
    public Object deleteBrandById(Integer id);

    //-----------------Brand---------------Brand-----------Brand---------
    //-----------------Brand---------------Brand-----------Brand---------

    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------

    /**
     * 管理员或用户根据id搜索分类
     *
     * @param id：Integer
     * @return GoodsCategory
     */
    public Object getGoodsCategoryById(Integer id);

    /**
     * 管理员或用户搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategory>
     */
    public Object listGoodsCategories(Integer page,
                                      Integer limit);

    /**
     * 管理员或用户搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategory>
     */
    public Object listOneLevelGoodsCategories(Integer page,
                                              Integer limit);

    /**
     * 管理员或用户搜索某一级分类下的所有二级分类
     *
     * @param id：Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return GoodsCategory
     */
    public Object listSecondLevelGoodsCategoryById(Integer id,
                                                   Integer page,
                                                   Integer limit);

    /**
     * 管理员新建分类
     *
     * @param goodsCategory：GoodsCategory
     * @return GoodsCategory
     */
    public Object addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 管理员修改分类
     *
     * @param id：Integer
     * @param goodsCategory：GoodsCategory
     * @return GoodsCategory
     */
    public Object updateGoodsCategoryById(Integer id, GoodsCategory goodsCategory);

    /**
     * 管理员删除分类
     *
     * @param id：Integer
     * @return GoodsCategory
     */
    public Object deleteGoodsCategory(Integer id);
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------
}
