package xmu.oomall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.service.GoodsService;

import java.util.List;


/**
 * @Author Ke
 * @Description: GoodsController
 * @create 2019/12/13 13:13
 */

@RestController
@RequestMapping("/goodsService")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    //-----------------Goods---------------Goods-----------Goods---------

    /**
     * 管理员或用户根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods，搜索到的商品，此URL与WX端是同一个URL
     */
    @GetMapping("/goods/{id}")
    public Object getGoodsById(@PathVariable Integer id) {
        return null;
    }

    /**
     * 内部接口————————————判断商品是否在售
     *
     * @param id：Integer
     * @return Integer，0表示该商品下架，1表示该商品在售，-1表示该商品不存在
     */
    @GetMapping("/goods/{id}/isOnSale")
    public Object isGoodsOnSale(@PathVariable Integer id) {
        return null;
    }

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
    @GetMapping("/goods")
    public Object listGoodsByCondition(@RequestParam String goodsSn,
                                       @RequestParam String goodsName,
                                       @RequestParam Integer status,
                                       @RequestParam Integer page,
                                       @RequestParam Integer limit) {
        return null;
    }

    /**
     * 用户根据条件搜素商品
     *
     * @param goodsSn:String   商品的序列号
     * @param goodsName:String 商品的名字
     * @param page:Integer     第几页
     * @param limit:Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表
     */
    @GetMapping("/goods")
    public Object listGoodsByCondition(@RequestParam String goodsSn,
                                       @RequestParam String goodsName,
                                       @RequestParam Integer page,
                                       @RequestParam Integer limit) {
        return null;
    }

    /**
     * 用户根据商品分类搜索商品
     *
     * @param id:Integer(PathVariable
     * @param page:Integer            第几页
     * @param limit:Integer           一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @GetMapping("/categories/{id}/goods")
    public Object ListGoodsByCategoryId(@PathVariable Integer id,
                                        @RequestParam Integer page,
                                        @RequestParam Integer limit) {
        return null;
    }

    /**
     * 管理员新建商品
     *
     * @param goodsPo：GoodsPo(RequestBody
     * @return GoodsPo，新建的商品
     */
    @PostMapping("/admin/goods")
    public Object addGoods(@RequestBody GoodsPo goodsPo) {
        return null;
    }

    /**
     * 管理员根据id修改商品
     *
     * @param id：Integer(PathVariable
     * @param goodsPo:GoodsPo(RequestBody
     * @return GoodsPo，修改后的商品
     */
    @PutMapping("/goods/{id}")
    public Object updateGoodsById(@PathVariable Integer id,
                                  @RequestBody GoodsPo goodsPo) {
        return null;
    }

    /**
     * 管理员根据id删除商品
     *
     * @param id：Integer(PathVariable
     * @return ResponseUtil.ok(xxx)或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/goods/{id}")
    public Object deleteGoodsById(@PathVariable Integer id) {
        return null;
    }
    //-----------------Goods---------------Goods-----------Goods---------
    //-----------------Goods---------------Goods-----------Goods---------

    //-----------------Product-------------Product-------------Product--------

    /**
     * 管理员根据id搜索产品
     *
     * @param id:Integer(PathVariable
     * @return Product，搜索到的产品
     */
    @GetMapping("/products/{id}")
    public Object getProductById(@PathVariable Integer id) {
        return null;
    }

    /**
     * 管理员搜索某个商品下的所有产品
     *
     * @param id:Integer(PathVariable
     * @param page:Integer            第几页
     * @param limit:Integer           一页多少
     * @return List<ProductPo>，所属该商品的产品列表
     */
    @GetMapping("/goods/{id}/products")
    public Object listProductsByGoodsId(@PathVariable Integer id,
                                        @RequestParam Integer page,
                                        @RequestParam Integer limit) {
        return null;
    }

    /**
     * 管理员新建某个商品下的产品
     *
     * @param id:Integer(PathVariable
     * @param productPo:ProductPo(RequestBody
     * @return ProductPo，新建的商品
     */
    @PostMapping("/goods/{id}/products")
    public Object addProduct(@PathVariable Integer id,
                             @RequestBody ProductPo productPo) {
        return null;
    }

    /**
     * 管理员根据id修改产品
     *
     * @param id:Integer(PathVariable
     * @param productPo:ProductPo(RequestBody
     * @return ProductPo，修改后的产品
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(@PathVariable Integer id,
                                    @RequestBody ProductPo productPo) {
        return null;
    }

    /**
     * 管理员根据id删除产品
     *
     * @param id:Integer(PathVariable
     * @return ResponseUtil.ok(xxx)或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id) {
        return null;
    }

    //-----------------Product-------------Product-------------Product--------
    //-----------------Product-------------Product-------------Product--------


    //-----------------Brand---------------Brand-----------Brand---------

    /**
     * 管理员或用户根据id搜索品牌
     *
     * @param id:Integer(PathVariable
     * @return Brand
     */
    @GetMapping("/brands/{id}")
    public Object getBrandById(@PathVariable Integer id) {
        return null;
    }

    /**
     * 管理员根据条件搜索品牌
     *
     * @param brandId:String   品牌的id
     * @param brandName:String 品牌的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<brand>,搜索到的品牌列表
     */
    @GetMapping("/admins/brands")
    public Object listBrandsByCondition(@RequestParam String brandId,
                                        @RequestParam String brandName,
                                        @RequestParam Integer page,
                                        @RequestParam Integer limit) {
        return null;
    }

    /**
     * 用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<brand>,搜索到的品牌列表
     */
    @GetMapping("/brands")
    public Object listBrandsByCondition(@RequestParam Integer page,
                                        @RequestParam Integer limit) {
        return null;
    }

    /**
     * 管理员创建品牌
     *
     * @param brandPo:BrandPo 要添加的品牌
     * @return BrandPo
     */
    @PostMapping("/brands")
    public Object addBrand(@RequestBody BrandPo brandPo) {
        return null;
    }

    /**
     * 管理员修改品牌
     *
     * @param id：Integer（PathVariable
     * @param brandPo：BrandPo（RequestBody
     * @return BrandPo
     */
    @PutMapping("/brands/{id}")
    public Object updateBrandById(@PathVariable Integer id, @RequestBody BrandPo brandPo) {
        return null;
    }

    /**
     * 管理员根据id删除品牌
     *
     * @param id：Integer
     * @return ResponseUtil.ok(xxx)或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/brands/{id}")
    public Object deleteBrandById(@PathVariable Integer id) {
        return null;
    }

    //-----------------Brand---------------Brand-----------Brand---------
    //-----------------Brand---------------Brand-----------Brand---------

    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------

    /**
     * 管理员或用户根据id搜索分类
     *
     * @param id：Integer
     * @return GoodsCategory
     */
    @GetMapping("/categories/{id}")
    public Object getGoodsCategoryById(@PathVariable Integer id) {
        return null;
    }

    /**
     * 管理员或用户搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategory>
     */
    @GetMapping("/categories")
    public Object listGoodsCategories(@RequestParam Integer page,
                                      @RequestParam Integer limit) {
        return null;
    }

    /**
     * 管理员或用户搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategory>
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategories(@RequestParam Integer page,
                                              @RequestParam Integer limit) {
        return null;
    }

    /**
     * 管理员或用户搜索某一级分类下的所有二级分类
     *
     * @param id：Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return GoodsCategory
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable Integer id,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer limit) {
        return null;
    }

    /**
     * 管理员新建分类
     *
     * @param goodsCategoryPo：GoodsCategoryPo
     * @return GoodsCategoryPo
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategoryPo goodsCategoryPo) {
        return null;
    }

    /**
     * 管理员修改分类
     *
     * @param id：Integer
     * @param goodsCategoryPo：GoodsCategoryPo
     * @return GoodsCategoryPo
     */
    @PutMapping("/categories/{id}")
    public Object updateGoodsCategoryById(@PathVariable Integer id, @RequestBody GoodsCategoryPo goodsCategoryPo) {
        return null;
    }

    /**
     * 管理员删除分类
     *
     * @param id：Integer
     * @return ResponseUtil.ok(xxx)或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteGoodsCategory(@PathVariable Integer id) {
        return null;
    }
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------
}
