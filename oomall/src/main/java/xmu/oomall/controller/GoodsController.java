package xmu.oomall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.vo.*;
import xmu.oomall.domain.*;
import xmu.oomall.service.*;

/**
 * @Author Ke
 */
@RestController
//@RequestMapping(value = "/goods", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
public class GoodsController {
    private  static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    /**
     * 根据id获得产品对象
     *
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public Object getProductById(@PathVariable Integer id){
        return goodsService.getProductById(id);
    }

    /**
     * 管理员查询商品下的产品
     *
     * @param id
     * @return
     */
    @GetMapping("/goods/{id}/products")
    public Object listProductByGoodsId(@PathVariable Integer id) {
        return goodsService.listProductByGoodsId(id);
    }

    /**
     * 管理员添加商品下的产品
     *
     * @param id
     * @return
     */
    @PostMapping("/goods/{id}/products")
    public Object addProductByGoodsId(@PathVariable Integer id,@RequestBody ProductVo productVo) {
        return goodsService.addProductByGoodsId(id,productVo);
    }

    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id
     * @return
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(@PathVariable Integer id,@RequestBody ProductVo productVo){
        return goodsService.updateProductById(id,productVo);
    }

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id) {
        return goodsService.deleteProductById(id);
    }

    /**
     * 新建商品
     *
     * @param goodsVo
     * @return
     */
    @PostMapping("/goods")
    public Object addGoods(@RequestBody GoodsVo goodsVo) {
        return goodsService.addGoods(goodsVo);
    }

    /**
     * 根据id获取某个商品
     *
     * @param id
     * @return
     */
    @GetMapping("/goods/{id}")
    public Object getGoodsById(@PathVariable Integer id) {
        return goodsService.getGoodsById(id);
    }

    /**
     * 根据id更新商品信息
     *
     * @param goodsVo
     * @return
     */
    @PutMapping("/goods/{id}")
    public Object updateGoodsById(@PathVariable Integer id, @RequestBody GoodsVo goodsVo) {
        return goodsService.updateGoodsById(id,goodsVo);
    }

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/goods/{id}")
    public Object deleteGoodsById(@PathVariable Integer id) {
        return goodsService.deleteGoodsById(id);
    }

    /**
     * 获取某一类别下的商品
     *
     * @return
     */
    @GetMapping("/categories/{id}/goods")
    public Object ListGoodsByCategoryId(@PathVariable Integer id) {
        return goodsService.listGoodsByCategoryId(id);
    }

    /**
     * 根据条件搜素商品
     *
     * @param goodsSn
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/goods")
    public Object listGoodsByCondition(String goodsSn, String name,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            @RequestParam(defaultValue = "add_time") String sort,
                            @RequestParam(defaultValue = "desc") String order) {
        return goodsService.listGoodsByCondition(goodsSn,name,page,limit);
    }

    /**
     * 根据条件搜索品牌
     *
     * @param id
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/admins/brands")
    public Object listBrandByCondition(String id, String name,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       @RequestParam(defaultValue = "add_time") String sort,
                                       @RequestParam(defaultValue = "desc") String order) {
        return goodsService.listBrandByCondition(id,name,page,limit);
    }


    /**
     * 创建一个品牌
     *
     * @param brand
     * @return
     */
    @PostMapping("/brands")
    public Object addBrand(@RequestBody Brand brand) {
        return goodsService.addBrand(brand);
    }

    /**
     * 查看品牌详情,此API与商城端/brands/{id}完全相同
     *
     * @param id
     * @return
     */
    @GetMapping("/brands/{id}")
    public Object getBrandById(@PathVariable Integer id) {
        return goodsService.getBrandById(id);
    }

    /**
     * 修改单个品牌的信息
     *
     * @param id
     * @param brand
     * @return
     */
    @PutMapping("/brands/{id}")
    public Object updateBrandById(@PathVariable Integer id, @RequestBody Brand brand) {
        return goodsService.updateBrandById(id,brand);
    }

    /**
     * 删除一个品牌
     *
     * @param id
     * @return
     */
    @DeleteMapping("/brands/{id}")
    public Object deleteBrandById(@PathVariable Integer id) {
        return goodsService.deleteBrandById(id);
    }

    /**
     * 查看所有的分类
     *
     * @return
     */
    @GetMapping("/categories")
    public Object listGoodsCategory() {
        return goodsService.listGoodsCategory();
    }

    /**
     * 新建一个分类
     *
     * @param goodsCategory
     * @return
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        return goodsService.addGoodsCategory(goodsCategory);
    }

    /**
     * 查看单个分类信息
     *
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    public Object getGoodsCategoryById(@PathVariable Integer id) {
        return goodsService.getGoodsCategoryById(id);
    }

    /**
     * 修改分类信息
     *
     * @param id
     * @param goodsCategory
     * @return
     */
    @PutMapping("/categories/{id}")
    public Object updateGoodsCategoryById(@PathVariable Integer id, @RequestBody GoodsCategory goodsCategory) {
        return goodsService.updateGoodsCategoryById(id,goodsCategory);
    }

    /**
     * 删除单个分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteGoodsCategory(@PathVariable Integer id) {
        return goodsService.deleteGoodsCategory(id);
    }

    /**
     * 查看所有一级分类
     *
     * @return
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategory() {
        return goodsService.listOneLevelGoodsCategory();
    }

    /**
     * 查看所有品牌
     *
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/brands")
    public Object listBrand(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            @RequestParam(defaultValue = "add_time") String sort,
                            @RequestParam(defaultValue = "desc") String order) {
        return goodsService.listBrand(page,limit);
    }

    /**
     * 获取当前一级分类下的二级分类
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @GetMapping("categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable Integer id) {
        return goodsService.listSecondLevelGoodsCategoryById(id);
    }


}
