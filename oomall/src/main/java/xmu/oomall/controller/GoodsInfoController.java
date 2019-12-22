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

    @GetMapping("/test")
    void test(HttpServletRequest request) {
        System.out.println(111);
        Log log = new Log(request.getIntHeader("userId"),
                request.getHeader("ip"), 0, "查询商品", 1, null);
        logClientService.addLog(log);
    }
    //-----------------Goods---------------Goods-----------Goods---------

    /**
     * 1.管理员根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods(可获取下架商品)
     */
    @GetMapping(value = "/admin/goods/{id}", produces = "application/json;charset=utf-8")
    public Object getGoodsById(@PathVariable Integer id,
                               HttpServletRequest request) {
        return goodsController.getGoodsById(id, request);
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
    @GetMapping(value = "/admin/goods", produces = "application/json;charset=utf-8")
    public Object listGoodsByCondition(@RequestParam(defaultValue = "", name = "goodsSn") String goodsSn,
                                       @RequestParam(defaultValue = "", name = "goodsName") String goodsName,
                                       @RequestParam(defaultValue = "1", name = "page") Integer page,
                                       @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                       HttpServletRequest request) {
        return goodsController.listGoodsByCondition(goodsSn, goodsName, page, limit, request);
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
        return goodsController.addGoods(goodsPo, request);
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
        return goodsController.updateGoodsById(id, goodsPo, request);
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
        return goodsController.deleteGoodsById(id, request);
    }

    /**
     * 1.用户根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods（不可获取下架商品）
     */
    @GetMapping(value = "/goods/{id}", produces = "application/json;charset=utf-8")
    public Object getGoodsForSaleById(@PathVariable Integer id, HttpServletRequest request) {
        return goodsController.getGoodsForSaleById(id, request);
    }

    /**
     * 2.用户根据条件搜素商品
     *
     * @param goodsName:String 商品的名字
     * @param page:Integer     第几页
     * @param limit:Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(不可获取下架商品)
     */
    @GetMapping(value = "/goods", produces = "application/json;charset=utf-8")
    public Object listGoodsForSaleByCondition(@RequestParam(defaultValue = "", name = "goodsName") String goodsName,
                                              @RequestParam(defaultValue = "1", name = "page") Integer page,
                                              @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                              HttpServletRequest request) {
        return goodsController.listGoodsForSaleByCondition(goodsName, page, limit, request);
    }


    /**
     * 3.用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id:Integer(PathVariable
     * @param page:Integer            第几页
     * @param limit:Integer           一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @GetMapping(value = "/categories/{id}/goods", produces = "application/json;charset=utf-8")
    public Object listGoodsForSaleByCategoryId(@PathVariable Integer id,
                                               @RequestParam(defaultValue = "1", name = "page") Integer page,
                                               @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                               HttpServletRequest request) {
        return goodsController.listGoodsForSaleByCategoryId(id, page, limit, request);
    }

    /**
     * 4.用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id:Integer(PathVariable
     * @param page:                   Integer 第几页
     * @param limit:                  Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @GetMapping(value = "/brands/{id}/goods", produces = "application/json;charset=utf-8")
    public Object listGoodsForSaleByBrandId(@PathVariable Integer id,
                                            @RequestParam(defaultValue = "1", name = "page") Integer page,
                                            @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                            HttpServletRequest request) {
        return goodsController.listGoodsForSaleByBrandId(id, page, limit, request);
    }

    /**
     * 1.判断商品是否在售（内部接口）
     *
     * @param id：Integer
     * @return Boolean
     */
    @GetMapping(value = "/goods/{id}/isOnSale", produces = "application/json;charset=utf-8")
    public Object isGoodsOnSale(@PathVariable Integer id) {
        return goodsController.isGoodsOnSale(id);
    }

    /**
     * 2.根据id搜索商品(内部接口)
     *
     * @param id：Integer(PathVariable
     * @return GoodsPo（不可获取下架商品）
     */
    @GetMapping(value = "/inner/goods/{id}", produces = "application/json;charset=utf-8")
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
    @GetMapping(value = "/goods/{id}/products", produces = "application/json;charset=utf-8")
    public Object listProductsByGoodsId(@PathVariable Integer id,
                                        @RequestParam(defaultValue = "1", name = "page") Integer page,
                                        @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                        HttpServletRequest request) {
        return productController.listProductsByGoodsId(id, page, limit, request);
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
        return productController.addProduct(id, productPo, request);
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
        return productController.updateProductById(id, productPo, request);
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
        return productController.deleteProductById(id, request);
    }

    /**
     * 1.用户根据id搜索产品（内部接口）
     *
     * @param id:Integer(PathVariable
     * @return Product，搜索到的产品
     */
    @GetMapping(value = "/user/product/{id}", produces = "application/json;charset=utf-8")
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
    public Object updateStockByProductId(@RequestBody List<OrderItem> orderItemList,
                                         @RequestParam(defaultValue = "false", name = "operation") boolean operation) {
        return productController.updateStockByProductId(orderItemList, operation);
    }
    //-----------------Brand---------------Brand-----------Brand---------

    /**
     * 1.管理员根据id搜索品牌
     *
     * @param id:Integer(PathVariable
     * @return BrandPo
     */
    @GetMapping(value = "/admin/brands/{id}", produces = "application/json;charset=utf-8")
    public Object getBrandById(@PathVariable Integer id,
                               HttpServletRequest request) {
        return brandController.getBrandById(id, request);
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
    @GetMapping(value = "/admin/brands", produces = "application/json;charset=utf-8")
    public Object listBrandsByCondition(@RequestParam(defaultValue = "", name = "brandId") String brandId,
                                        @RequestParam(defaultValue = "", name = "brandName") String brandName,
                                        @RequestParam(defaultValue = "1", name = "page") Integer page,
                                        @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                        HttpServletRequest request) {
        return brandController.listBrandsByCondition(brandId, brandName, page, limit, request);
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
        return brandController.addBrand(brandPo, request);
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
        return brandController.updateBrandById(id, brandPo, request);
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
        return brandController.deleteBrandById(id, request);
    }

    /**
     * 1.用户根据id搜索品牌
     *
     * @param id:Integer(PathVariable
     * @return BrandPo
     */
    @GetMapping(value = "/brands/{id}", produces = "application/json;charset=utf-8")
    public Object getBrandForUserById(@PathVariable Integer id,
                                      HttpServletRequest request) {
        return brandController.getBrandForUserById(id, request);
    }

    /**
     * 2.用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @GetMapping(value = "/brands", produces = "application/json;charset=utf-8")
    public Object listBrandsByCondition(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                        @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                        HttpServletRequest request) {
        return brandController.listBrandsByCondition(page, limit, request);
    }

    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------

    /**
     * 1.管理员根据id搜索分类
     *
     * @param id：Integer
     * @return GoodsCategoryPo
     */
    @GetMapping(value = "/categories/{id}", produces = "application/json;charset=utf-8")
    public Object getGoodsCategoryById(@PathVariable Integer id,
                                       HttpServletRequest request) {
        return goodsCategoryController.getGoodsCategoryById(id, request);
    }

    /**
     * 2.管理员搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping(value = "/categories", produces = "application/json;charset=utf-8")
    public Object listGoodsCategories(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                      @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                      HttpServletRequest request) {
        return goodsCategoryController.listGoodsCategories(page, limit, request);
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
        return goodsCategoryController.addGoodsCategory(goodsCategoryPo, request);
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
        return goodsCategoryController.updateGoodsCategoryById(id, goodsCategoryPo, request);
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
        return goodsCategoryController.updateGoodsCategoryPidById(id, goodsCategoryPo, request);
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
        return goodsCategoryController.deleteGoodsCategoryById(id, request);
    }

    /**
     * 1.用户搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping(value = "/categories/l1", produces = "application/json;charset=utf-8")
    public Object listOneLevelGoodsCategories(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                              @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                              HttpServletRequest request) {
        return goodsCategoryController.listOneLevelGoodsCategories(page, limit, request);
    }

    /**
     * 2.用户搜索某一级分类下的所有二级分类
     *
     * @param id：Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping(value = "/categories/l1/{id}/l2", produces = "application/json;charset=utf-8")
    public Object listSecondLevelGoodsCategoryById(@PathVariable Integer id,
                                                   @RequestParam(defaultValue = "1", name = "page") Integer page,
                                                   @RequestParam(defaultValue = "10", name = "limit") Integer limit,
                                                   HttpServletRequest request) {
        return goodsCategoryController.listSecondLevelGoodsCategoryById(id, page, limit, request);
    }
}