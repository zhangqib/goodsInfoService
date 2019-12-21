package xmu.oomall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.feign.AdClientService;
import xmu.oomall.controller.feign.LogClientService;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Author Ke
 * @Description: GoodsInfoController
 * @create 2019/12/20 18:22
 */

@RestController
@RequestMapping(value = "/goodsInfoService")
public class GoodsInfoController {
    @Autowired
    private AdClientService adClientService;

    @Autowired
    private LogClientService logClientService;

    @Autowired
    private GoodsController goodsController;
    @Autowired
    private ProductController productController;
    @Autowired
    private BrandController brandController;
    @Autowired
    private GoodsCategoryController goodsCategoryController;
    //-----------------Goods---------------Goods-----------Goods---------

    /**
     * 1.管理员根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods(可获取下架商品)
     */
    @GetMapping("/admin/goods/{id}")
    public Object getGoodsById(@PathVariable Integer id,
                               HttpServletRequest request) {
        return goodsController.getGoodsById(id,request);
    }

    /**
     * 2.管理员根据条件搜索商品
     *
     * @param goodsSn:String   商品的序列号
     * @param goodsName:String 商品的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(可获取下架商品)
     */
    @GetMapping("/admin/goods")
    public Object listGoodsByCondition(@RequestParam String goodsSn, @RequestParam String goodsName,
                                       @RequestParam(defaultValue = "1", name = "page") Integer page,
                                       @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                       HttpServletRequest request) {
        return goodsController.listGoodsByCondition(goodsSn, goodsName, page, limit,request);
    }

    /**
     * 3.管理员新建商品
     *
     * @param goodsPo：GoodsPo(RequestBody
     * @return GoodsPo，新建的商品
     */
    @PostMapping("/goods")
    public Object addGoods(@RequestBody GoodsPo goodsPo,
                           HttpServletRequest request) {
        return goodsController.addGoods(goodsPo,request);
    }

    /**
     * 4.管理员根据id修改商品
     *
     * @param id：Integer(PathVariable
     * @param goodsPo:GoodsPo(RequestBody
     * @return GoodsPo，修改后的商品
     */
    @PutMapping("/goods/{id}")
    public Object updateGoodsById(@PathVariable Integer id,
                                  @RequestBody GoodsPo goodsPo,
                                  HttpServletRequest request) {
        return goodsController.updateGoodsById(id, goodsPo,request);
    }

    /**
     * 5.管理员根据id删除商品
     *
     * @param id：Integer(PathVariable
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    @DeleteMapping("/goods/{id}")
    public Object deleteGoodsById(@PathVariable Integer id,
                                  HttpServletRequest request) {
        return goodsController.deleteGoodsById(id,request);
    }

    /**
     * 1.用户根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods（不可获取下架商品）
     */
    @GetMapping("/goods/{id}")
    public Object getGoodsForSaleById(@PathVariable Integer id) {
        return goodsController.getGoodsForSaleById(id);
    }

    /**
     * 2.用户根据条件搜素商品
     *
     * @param goodsName:String 商品的名字
     * @param page:Integer     第几页
     * @param limit:Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(不可获取下架商品)
     */
    @GetMapping("/goods")
    public Object listGoodsForSaleByCondition(@RequestParam String goodsName,
                                              @RequestParam(defaultValue = "1", name = "page") Integer page,
                                              @RequestParam(defaultValue = "10", name = "limit") Integer limit) {
        return goodsController.listGoodsForSaleByCondition(goodsName, page, limit);
    }


    /**
     * 3.用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id:Integer(PathVariable
     * @param page:Integer            第几页
     * @param limit:Integer           一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @GetMapping("/categories/{id}/goods")
    public Object listGoodsForSaleByCategoryId(@PathVariable Integer id,
                                               @RequestParam(defaultValue = "1", name = "page") Integer page,
                                               @RequestParam(defaultValue = "10", name = "limit") Integer limit) {
        return goodsController.listGoodsForSaleByCategoryId(id, page, limit);
    }

    /**
     * 4.用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id:Integer(PathVariable
     * @param page:                   Integer 第几页
     * @param limit:                  Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @GetMapping("/brands/{id}/goods")
    public Object listGoodsForSaleByBrandId(@PathVariable Integer id,
                                            @RequestParam(defaultValue = "1", name = "page") Integer page,
                                            @RequestParam(defaultValue = "10", name = "limit") Integer limit) {
        return goodsController.listGoodsForSaleByBrandId(id, page, limit);
    }

    /**
     * 1.判断商品是否在售（内部接口）
     *
     * @param id：Integer
     * @return Boolean
     */
    @GetMapping("/goods/{id}/isOnSale")
    public Object isGoodsOnSale(@PathVariable Integer id) {
        return goodsController.isGoodsOnSale(id);
    }

    /**
     * 2.根据id搜索商品(内部接口)
     *
     * @param id：Integer(PathVariable
     * @return GoodsPo（不可获取下架商品）
     */
    @GetMapping("/inner/goods/{id}")
    public Object getGoodsInnerById(@PathVariable Integer id) {
        return goodsController.getGoodsInnerById(id);
    }

    //-----------------Product-------------Product-------------Product--------

    /**
     * 1.管理员搜索某个商品下的所有产品
     *
     * @param id:Integer(PathVariable
     * @param page:Integer            第几页
     * @param limit:Integer           一页多少
     * @return List<ProductPo>，所属该商品的产品列表
     */
    @GetMapping("/goods/{id}/products")
    public Object listProductsByGoodsId(@PathVariable Integer id,
                                        @RequestParam(defaultValue = "1", name = "page") Integer page,
                                        @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                        HttpServletRequest request) {
        return productController.listProductsByGoodsId(id, page, limit,request);
    }


    /**
     * 2.管理员新建某个商品下的产品
     *
     * @param id:Integer(PathVariable
     * @param productPo:ProductPo(RequestBody
     * @return ProductPo，新建的商品
     */
    @PostMapping("/goods/{id}/products")
    public Object addProduct(@PathVariable Integer id,
                             @RequestBody ProductPo productPo,
                             HttpServletRequest request) {
        return productController.addProduct(id, productPo,request);
    }

    /**
     * 3.管理员根据id修改产品
     *
     * @param id:Integer(PathVariable
     * @param productPo:ProductPo(RequestBody
     * @return ProductPo，修改后的产品
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(@PathVariable Integer id,
                                    @RequestBody ProductPo productPo,
                                    HttpServletRequest request) {
        return productController.updateProductById(id, productPo,request);
    }

    /**
     * 4.管理员根据id删除产品
     *
     * @param id:Integer(PathVariable
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id,
                                    HttpServletRequest request) {
        return productController.deleteProductById(id,request);
    }

    /**
     * 1.用户根据id搜索产品（内部接口）
     *
     * @param id:Integer(PathVariable
     * @return Product，搜索到的产品
     */
    @GetMapping("/user/product/{id}")
    public Object getProductById(@PathVariable Integer id) {
        return productController.getProductById(id);
    }

    /**
     * 2.根据id修改Product的库存（内部接口）
     *
     * @param orderItemList：List<OrderItem>(根据orderItemList来修改库存
     * @param operation:boolean(true加库存，false减库存
     * @return Boolean ，修改成功与否
     */
    @PutMapping("/product/list/deduct")
    public Object updateStockByProductId(@RequestBody List<OrderItem> orderItemList, @RequestParam boolean operation) {
        return productController.updateStockByProductId(orderItemList, operation);
    }
    //-----------------Brand---------------Brand-----------Brand---------

    /**
     * 1.管理员根据id搜索品牌
     *
     * @param id:Integer(PathVariable
     * @return BrandPo
     */
    @GetMapping("/admin/brands/{id}")
    public Object getBrandById(@PathVariable Integer id,
                               HttpServletRequest request) {
        return brandController.getBrandById(id,request);
    }

    /**
     * 2.管理员根据条件搜索品牌
     *
     * @param brandId:String   品牌的id
     * @param brandName:String 品牌的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @GetMapping("/admin/brands")
    public Object listBrandsByCondition(@RequestParam String brandId, @RequestParam String brandName,
                                        @RequestParam(defaultValue = "1", name = "page") Integer page,
                                        @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                        HttpServletRequest request) {
        return brandController.listBrandsByCondition(brandId, brandName, page, limit,request);
    }

    /**
     * 3.管理员创建品牌
     *
     * @param brandPo:BrandPo 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @PostMapping("/brands")
    public Object addBrand(@RequestBody BrandPo brandPo,
                           HttpServletRequest request) {
        return brandController.addBrand(brandPo,request);
    }

    /**
     * 4.管理员修改品牌
     *
     * @param id：Integer（PathVariable
     * @param brandPo：BrandPo（RequestBody)(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @PutMapping("/brands/{id}")
    public Object updateBrandById(@PathVariable Integer id,
                                  @RequestBody BrandPo brandPo,
                                  HttpServletRequest request) {
        return brandController.updateBrandById(id, brandPo,request);
    }

    /**
     * 5.管理员根据id删除品牌
     *
     * @param id：Integer
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    @DeleteMapping("/brands/{id}")
    public Object deleteBrandById(@PathVariable Integer id,
                                  HttpServletRequest request) {
        return brandController.deleteBrandById(id,request);
    }

    /**
     * 1.用户根据id搜索品牌
     *
     * @param id:Integer(PathVariable
     * @return BrandPo
     */
    @GetMapping("/brands/{id}")
    public Object getBrandForUserById(@PathVariable Integer id) {
        return brandController.getBrandForUserById(id);
    }

    /**
     * 2.用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @GetMapping("/brands")
    public Object listBrandsByCondition(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                        @RequestParam(defaultValue = "10", name = "limit") Integer limit) {
        return brandController.listBrandsByCondition(page, limit);
    }

    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------

    /**
     * 1.管理员根据id搜索分类
     *
     * @param id：Integer
     * @return GoodsCategoryPo
     */
    @GetMapping("/categories/{id}")
    public Object getGoodsCategoryById(@PathVariable Integer id,
                                       HttpServletRequest request) {
        return goodsCategoryController.getGoodsCategoryById(id,request);
    }

    /**
     * 2.管理员搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories")
    public Object listGoodsCategories(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                      @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                      HttpServletRequest request) {
        return goodsCategoryController.listGoodsCategories(page, limit,request);
    }

    /**
     * 3.管理员新建分类
     *
     * @param goodsCategoryPo：GoodsCategoryPo(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategoryPo goodsCategoryPo,
                                   HttpServletRequest request) {
        return goodsCategoryController.addGoodsCategory(goodsCategoryPo,request);
    }

    /**
     * 4.管理员修改分类
     *
     * @param id：Integer
     * @param goodsCategoryPo：GoodsCategoryPo(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @PutMapping("/categories/{id}")
    public Object updateGoodsCategoryById(@PathVariable Integer id,
                                          @RequestBody GoodsCategoryPo goodsCategoryPo,
                                          HttpServletRequest request) {
        return goodsCategoryController.updateGoodsCategoryById(id, goodsCategoryPo,request);
    }

    /**
     * 5.管理员修改子分类在父分类下的位置
     *
     * @param id：Integer
     * @param goodsCategoryPo：GoodsCategoryPo(body包含pid(只能修改pid不为空的项，且修改后pid不可为空))
     * @return GoodsCategoryPo
     */
    @PutMapping("/categories/l2/{id}")
    public Object updateGoodsCategoryPidById(@PathVariable Integer id,
                                             @RequestBody GoodsCategoryPo goodsCategoryPo,
                                             HttpServletRequest request) {
        return goodsCategoryController.updateGoodsCategoryPidById(id, goodsCategoryPo,request);
    }

    /**
     * 6.管理员删除分类
     *
     * @param id：Integer
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteGoodsCategoryById(@PathVariable Integer id,
                                          HttpServletRequest request) {
        return goodsCategoryController.deleteGoodsCategoryById(id,request);
    }

    /**
     * 1.用户搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategories(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                              @RequestParam(defaultValue = "10", name = "limit") Integer limit) {
        return goodsCategoryController.listOneLevelGoodsCategories(page, limit);
    }

    /**
     * 2.用户搜索某一级分类下的所有二级分类
     *
     * @param id：Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable Integer id,
                                                   @RequestParam(defaultValue = "1", name = "page") Integer page,
                                                   @RequestParam(defaultValue = "10", name = "limit") Integer limit) {
        return goodsCategoryController.listSecondLevelGoodsCategoryById(id, page, limit);
    }
}