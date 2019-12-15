package xmu.oomall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.*;
import xmu.oomall.service.*;

import xmu.oomall.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Ke
 * @Description: GoodsController
 * @create 2019/12/15 18:22
 */

@RestController
@RequestMapping("/goodsService")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    //-----------------Goods---------------Goods-----------Goods---------

    /**
     * 用户根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods（不可获取下架商品）
     */
    @GetMapping("/goods/{id}")
    public Object getGoodsForSaleById(@PathVariable Integer id) {
        if (id == null) {
            //401
            return ResponseUtil.badArgument();
        } else {
            Goods goods = goodsService.getGoodsForSaleById(id);
            if (goods == null) {
                //402
                return ResponseUtil.badArgumentValue();
            } else {
                //1.获取userId
                //2.有的话调用足迹服务（@PostMapping("/footprints") ），没有的话跳过
                return ResponseUtil.ok(goods);
            }
        }
    }

    /**
     * 管理员根据id搜索商品
     *
     * @param id：Integer(PathVariable
     * @return Goods(可获取下架商品)
     */
    @GetMapping("/admin/goods/{id}")
    public Object getGoodsById(@PathVariable Integer id) {
        if (id == null) {
            //401
            return ResponseUtil.badArgument();
        } else {
            Goods goods = goodsService.getGoodsById(id);
            if (goods == null) {
                //402
                return ResponseUtil.badArgumentValue();
            } else {
                return ResponseUtil.ok(goods);
            }
        }
    }

    /**
     * 内部接口————————————判断商品是否在售
     *
     * @param id：Integer
     * @return Boolean
     */
    @GetMapping("/goods/{id}/isOnSale")
    public Object isGoodsOnSale(@PathVariable Integer id) {
        if (id == null) {
            //401
            return ResponseUtil.badArgument();
        } else {
            Goods goods = goodsService.getGoodsById(id);
            if (goods == null) {
                //402
                return ResponseUtil.badArgumentValue();
            } else {
                //商品不在售
                if (goods.getStatusCode() == 0) {
                    return ResponseUtil.ok(false);
                } else {
                    return ResponseUtil.ok(true);
                }
            }
        }
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
    @GetMapping("/admin/goods")
    public Object listGoodsByCondition(@RequestParam String goodsSn,
                                       @RequestParam String goodsName,
                                       @RequestParam Integer status,
                                       @RequestParam Integer page,
                                       @RequestParam Integer limit) {
        List<Goods> listGoods = goodsService.listGoodsByCondition(goodsSn, goodsName, status, page, limit);
        List<GoodsPo> listGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
        for (Goods goods : listGoods) {
            listGoodsPo.add(goods);
        }
        return ResponseUtil.ok(listGoodsPo);
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
        List<Goods> listGoods = goodsService.listGoodsByCondition(goodsSn, goodsName, null, page, limit);
        List<GoodsPo> listGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
        for (Goods goods : listGoods) {
            listGoodsPo.add(goods);
        }
        return ResponseUtil.ok(listGoodsPo);
    }

    /**
     * 用户根据商品分类id搜索该分类下的所有商品
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
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            List<Goods> listGoods = goodsService.listGoodsByCategoryId(id, page, limit);
            List<GoodsPo> listGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
            for (Goods goods : listGoods) {
                listGoodsPo.add(goods);
            }
            return ResponseUtil.ok(listGoodsPo);
        }
    }

    /**
     * 管理员或用户根据品牌id搜索该品牌下的所有商品
     *
     * @param id:Integer(PathVariable
     * @param page:                   Integer 第几页
     * @param limit:                  Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @GetMapping("/brands/{id}/goods")
    public Object ListGoodsByBrandId(@PathVariable Integer id,
                                     @RequestParam Integer page,
                                     @RequestParam Integer limit) {
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            List<Goods> listGoods = goodsService.listGoodsByBrandId(id, page, limit);
            List<GoodsPo> listGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
            for (Goods goods : listGoods) {
                listGoodsPo.add(goods);
            }
            return ResponseUtil.ok(listGoodsPo);
        }
    }

    /**
     * 管理员新建商品
     *
     * @param goodsPo：GoodsPo(RequestBody
     * @return GoodsPo，新建的商品
     */
    @PostMapping("/goods")
    public Object addGoods(@RequestBody GoodsPo goodsPo) {
        if (goodsPo == null) {
            return ResponseUtil.badArgument();
        } else {
            Goods goods = (Goods) goodsPo;
            goods = goodsService.addGoods(goods);
            return ResponseUtil.ok(goods);
        }
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
        if (id == null || goodsPo == null) {
            return ResponseUtil.badArgument();
        } else {
            goodsPo.setId(id);
            Goods goods = (Goods) goodsPo;
            goods = goodsService.updateGoodsById(goods);
            if (goods == null) {
                return ResponseUtil.updatedDataFailed();
            } else {
                return ResponseUtil.ok(goods);
            }
        }
    }

    /**
     * 管理员根据id删除商品
     *
     * @param id：Integer(PathVariable
     * @return ResponseUtil.ok()或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/goods/{id}")
    public Object deleteGoodsById(@PathVariable Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            if (goodsService.deleteGoodsById(id) == -1) {
                return ResponseUtil.badArgumentValue();
            } else if (goodsService.deleteGoodsById(id) == 0) {
                return ResponseUtil.fail(505, "更新数据失败，因为未下架商品无法删除。");
            } else {
                return ResponseUtil.ok();
            }
        }
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
        if (id == null) {
            //401
            return ResponseUtil.badArgument();
        } else {
            Product product = goodsService.getProductById(id);
            if (product == null) {
                //402
                return ResponseUtil.badArgumentValue();
            } else {
                return ResponseUtil.ok(product);
            }
        }
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
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            List<Product> listProducts = goodsService.listProductsByGoodsId(id, page, limit);
            List<ProductPo> listProductPos = new ArrayList<ProductPo>(listProducts.size());
            for (Product product : listProducts) {
                listProductPos.add(product);
            }
            return ResponseUtil.ok(listProductPos);
        }
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
        if (id == null || productPo == null) {
            return ResponseUtil.badArgument();
        } else {
            productPo.setGoodsId(id);
            Product product = (Product) productPo;
            product = goodsService.addProduct(product);
            return ResponseUtil.ok(product);
        }
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
        if (id == null || productPo == null) {
            return ResponseUtil.badArgument();
        } else {
            productPo.setId(id);
            Product product = (Product) productPo;
            product = goodsService.updateProductById(product);
            if (product == null) {
                return ResponseUtil.updatedDataFailed();
            } else {
                return ResponseUtil.ok(product);
            }
        }
    }

    /**
     * 管理员根据id删除产品
     *
     * @param id:Integer(PathVariable
     * @return ResponseUtil.ok(xxx)或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(@PathVariable Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            if (goodsService.deleteProductById(id)) {
                return ResponseUtil.ok();
            } else {
                return ResponseUtil.badArgumentValue();
            }
        }
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
        if (id == null) {
            //401
            return ResponseUtil.badArgument();
        } else {
            Brand brand = goodsService.getBrandById(id);
            if (brand == null) {
                //402
                return ResponseUtil.badArgumentValue();
            } else {
                return ResponseUtil.ok(brand);
            }
        }
    }

    /**
     * 管理员根据条件搜索品牌
     *
     * @param brandId:String   品牌的id
     * @param brandName:String 品牌的名字
     * @param page:            Integer 第几页
     * @param limit:           Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @GetMapping("/admins/brands")
    public Object listBrandsByCondition(@RequestParam String brandId,
                                        @RequestParam String brandName,
                                        @RequestParam Integer page,
                                        @RequestParam Integer limit) {
        List<Brand> listBrands = goodsService.listBrandsByCondition(brandId, brandName, page, limit);
        List<BrandPo> listBrandPos = new ArrayList<BrandPo>(listBrands.size());
        for (Brand brand : listBrands) {
            listBrandPos.add(brand);
        }
        return ResponseUtil.ok(listBrandPos);
    }

    /**
     * 用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @GetMapping("/brands")
    public Object listBrandsByCondition(@RequestParam Integer page,
                                        @RequestParam Integer limit) {
        List<Brand> listBrands = goodsService.listBrandsByCondition(null, null, page, limit);
        List<BrandPo> listBrandPos = new ArrayList<BrandPo>(listBrands.size());
        for (Brand brand : listBrands) {
            listBrandPos.add(brand);
        }
        return ResponseUtil.ok(listBrandPos);
    }

    /**
     * 管理员创建品牌
     *
     * @param brandPo:BrandPo 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @PostMapping("/brands")
    public Object addBrand(@RequestBody BrandPo brandPo) {
        if (brandPo == null) {
            return ResponseUtil.badArgument();
        } else {
            Brand brand = (Brand) brandPo;
            brand = goodsService.addBrand(brand);
            return ResponseUtil.ok(brand);
        }
    }

    /**
     * 管理员修改品牌
     *
     * @param id：Integer（PathVariable
     * @param brandPo：BrandPo（RequestBody)(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @PutMapping("/brands/{id}")
    public Object updateBrandById(@PathVariable Integer id, @RequestBody BrandPo brandPo) {
        if (id == null || brandPo == null) {
            return ResponseUtil.badArgument();
        } else {
            brandPo.setId(id);
            Brand brand = (Brand) brandPo;
            brand = goodsService.updateBrandById(brand);
            if (brand == null) {
                return ResponseUtil.updatedDataFailed();
            } else {
                return ResponseUtil.ok(brand);
            }
        }
    }

    /**
     * 管理员根据id删除品牌
     *
     * @param id：Integer
     * @return ResponseUtil.ok(xxx)或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/brands/{id}")
    public Object deleteBrandById(@PathVariable Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            if (goodsService.deleteBrandById(id)) {
                return ResponseUtil.ok();
            } else {
                return ResponseUtil.badArgumentValue();
            }
        }
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
        if (id == null) {
            //401
            return ResponseUtil.badArgument();
        } else {
            GoodsCategory goodsCategory = goodsService.getGoodsCategoryById(id);
            if (goodsCategory == null) {
                //402
                return ResponseUtil.badArgumentValue();
            } else {
                return ResponseUtil.ok(goodsCategory);
            }
        }
    }

    /**
     * 管理员或用户搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories")
    public Object listGoodsCategories(@RequestParam Integer page,
                                      @RequestParam Integer limit) {
        List<GoodsCategory> listGoodsCategories = goodsService.listGoodsCategories(page, limit);
        List<GoodsCategoryPo> listGoodsCategoryPos = new ArrayList<GoodsCategoryPo>(listGoodsCategories.size());
        for (GoodsCategory goodsCategory : listGoodsCategories) {
            listGoodsCategoryPos.add(goodsCategory);
        }
        return ResponseUtil.ok(listGoodsCategoryPos);
    }

    /**
     * 内部接口————————搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategories(@RequestParam Integer page,
                                              @RequestParam Integer limit) {
        List<GoodsCategory> listGoodsCategories = goodsService.listOneLevelGoodsCategories(page, limit);
        List<GoodsCategoryPo> listGoodsCategoryPos = new ArrayList<GoodsCategoryPo>(listGoodsCategories.size());
        for (GoodsCategory goodsCategory : listGoodsCategories) {
            listGoodsCategoryPos.add(goodsCategory);
        }
        return ResponseUtil.ok(listGoodsCategoryPos);
    }

    /**
     * 管理员或用户搜索某一级分类下的所有二级分类
     *
     * @param id：Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories/l1/{id}/l2")
    public Object listSecondLevelGoodsCategoryById(@PathVariable Integer id,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer limit) {
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            List<GoodsCategory> listGoodsCategories = goodsService.listSecondLevelGoodsCategoryById(id, page, limit);
            List<GoodsCategoryPo> listGoodsCategoryPos = new ArrayList<GoodsCategoryPo>(listGoodsCategories.size());
            for (GoodsCategory goodsCategory : listGoodsCategories) {
                listGoodsCategoryPos.add(goodsCategory);
            }
            return ResponseUtil.ok(listGoodsCategoryPos);
        }

    }

    /**
     * 管理员新建分类
     *
     * @param goodsCategoryPo：GoodsCategoryPo(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(@RequestBody GoodsCategoryPo goodsCategoryPo) {
        if (goodsCategoryPo == null) {
            return ResponseUtil.badArgument();
        } else {
            GoodsCategory goodsCategory = (GoodsCategory) goodsCategoryPo;
            goodsCategory = goodsService.addGoodsCategory(goodsCategory);
            return ResponseUtil.ok(goodsCategory);
        }
    }

    /**
     * 管理员修改分类
     *
     * @param id：Integer
     * @param goodsCategoryPo：GoodsCategoryPo(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @PutMapping("/categories/{id}")
    public Object updateGoodsCategoryById(@PathVariable Integer id,
                                          @RequestBody GoodsCategoryPo goodsCategoryPo) {
        if (id == null || goodsCategoryPo == null) {
            return ResponseUtil.badArgument();
        } else {
            goodsCategoryPo.setId(id);
            GoodsCategory goodsCategory = (GoodsCategory) goodsCategoryPo;
            goodsCategory = goodsService.updateGoodsCategoryById(goodsCategory);
            if (goodsCategory == null) {
                return ResponseUtil.updatedDataFailed();
            } else {
                return ResponseUtil.ok(goodsCategory);
            }
        }
    }

    /**
     * 管理员删除分类
     *
     * @param id：Integer
     * @return ResponseUtil.ok(xxx)或者ResponseUtil.fail(xxx)
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteGoodsCategory(@PathVariable Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        } else {
            if (goodsService.deleteGoodsCategoryById(id)) {
                return ResponseUtil.ok();
            } else {
                return ResponseUtil.badArgumentValue();
            }
        }
    }
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------
    //-----------------GoodsCategory---------------GoodsCategory-----------GoodsCategory---------
}
