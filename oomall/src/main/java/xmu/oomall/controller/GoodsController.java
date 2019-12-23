package xmu.oomall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.oomall.controller.feign.LogClientService;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.service.GoodsInfoService;
import xmu.oomall.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Ke
 * @Description: GoodsController
 * @create 2019/12/20
 */

@Component
public class GoodsController {
    @Autowired
    private LogClientService logClientService;
    @Autowired
    private GoodsInfoService goodsInfoService;

    /**
     * 1.管理员根据id搜索商品
     *
     * @param id：Integer
     * @return Goods(可获取下架商品)
     */
    public Object getGoodsById(Integer id, HttpServletRequest request) {
        GoodsPo goods = goodsInfoService.getGoodsById(id);
        if (goods != null) {
            Goods goodsPojo = new Goods(goods);
            //Brand
            BrandPo brandPo = goodsInfoService.getBrandById(goods.getBrandId());
            goodsPojo.setBrandPo(brandPo);
            //GoodsCategory
            GoodsCategoryPo goodsCategoryPo = goodsInfoService.getGoodsCategoryById(goods.getGoodsCategoryId());
            goodsPojo.setGoodsCategoryPo(goodsCategoryPo);
            goodsPojo.setGrouponRule(null);
            goodsPojo.setPresaleRule(null);
            List<ProductPo> retProductPoList = goodsInfoService.listProductsByGoodsId(id, 1, Integer.MAX_VALUE);
            goodsPojo.setProductPoList(retProductPoList);
            goodsPojo.setShareRule(null);
            Object retObj = ResponseUtil.ok(goodsPojo);
            return retObj;
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询商品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(775, "该商品不存在");
            return retObj;
        }
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
    public Object listGoodsByCondition(String goodsSn, String goodsName, Integer page, Integer limit,
                                       HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询商品", 1, null);
            logClientService.addLog(log);
            if ("".equals(goodsSn)) {
                goodsSn = null;
            }
            if ("".equals(goodsName)) {
                goodsName = null;
            }
            List<GoodsPo> retGoodsList = goodsInfoService.listGoodsByCondition(goodsSn, goodsName, page, limit);
            Object retObj = ResponseUtil.ok(retGoodsList);
            return retObj;
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询商品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(776, "分页参数错误，获取商品列表失败");
            return retObj;
        }
    }

    /**
     * 3.管理员新建商品
     *
     * @param goodsPo：GoodsPo
     * @return GoodsPo，新建的商品
     */
    public Object addGoods(GoodsPo goodsPo, HttpServletRequest request) {
        if (goodsPo != null) {
            GoodsPo retGoods = goodsInfoService.addGoods(goodsPo);
            if (retGoods != null) {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 1, "新建商品", 1, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.ok(retGoods);
                return retObj;
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 1, "新建商品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(771, "数据库操作失败,商品新建失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 1, "新建商品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(771, "前端传入数据为null,商品新建失败");
            return retObj;
        }
    }

    /**
     * 4.管理员根据id修改商品
     *
     * @param id：Integer
     * @param goodsPo:GoodsPo
     * @return GoodsPo，修改后的商品
     */
    public Object updateGoodsById(Integer id, GoodsPo goodsPo, HttpServletRequest request) {
        if (goodsPo != null) {
            GoodsPo goodsOld = goodsInfoService.getGoodsById(id);
            if (goodsOld != null) {
                goodsPo.setId(id);
                GoodsPo retGoods = goodsInfoService.updateGoodsById(goodsOld, goodsPo);
                if (retGoods != null) {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 2, "修改商品", 1, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok(retGoods);
                    return retObj;
                } else {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 2, "修改商品", 0, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(772, "数据库操作失败,商品修改失败");
                    return retObj;
                }
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 2, "修改商品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(772, "该商品不存在,商品修改失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 2, "修改商品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(772, "前端传入数据为null,商品修改失败");
            return retObj;
        }
    }

    /**
     * 5.管理员根据id删除商品
     *
     * @param id：Integer
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    public Object deleteGoodsById(Integer id, HttpServletRequest request) {
        GoodsPo goods = goodsInfoService.getGoodsById(id);
        if (goods != null) {
            Integer ret = goodsInfoService.deleteGoodsById(goods);
            if (ret == 1) {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 3, "删除商品", 1, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.ok();
                return retObj;
            } else if (ret == -1) {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 3, "删除商品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(773, "数据库操作失败,商品删除失败");
                return retObj;
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 3, "删除商品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(773, "该商品未下架,商品删除失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 3, "删除商品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(773, "该商品不存在,商品删除失败");
            return retObj;
        }
    }

    /**
     * 1.用户根据id搜索商品
     *
     * @param id：Integer
     * @return Goods（不可获取下架商品）
     */
    public Object getGoodsForSaleById(Integer id, HttpServletRequest request) {
        Integer userId = null;
        String userId2 = request.getHeader("userId");
        if (userId2 != null) {
            userId = Integer.parseInt(userId2);
        }
        GoodsPo goods = goodsInfoService.getGoodsForSaleById(userId, id);
        if (goods != null) {
            Goods goodsPojo = new Goods(goods);
            //Brand
            BrandPo brandPo = goodsInfoService.getBrandById(goods.getBrandId());
            goodsPojo.setBrandPo(brandPo);
            //GoodsCategory
            GoodsCategoryPo goodsCategoryPo = goodsInfoService.getGoodsCategoryById(goods.getGoodsCategoryId());
            goodsPojo.setGoodsCategoryPo(goodsCategoryPo);
            goodsPojo.setGrouponRule(null);
            goodsPojo.setPresaleRule(null);
            List<ProductPo> retProductPoList = goodsInfoService.listProductsByGoodsId(id, 1, Integer.MAX_VALUE);
            goodsPojo.setProductPoList(retProductPoList);
            goodsPojo.setShareRule(null);
            Object retObj = ResponseUtil.ok(goodsPojo);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(775, "该商品不存在");
            return retObj;
        }
    }

    /**
     * 2.用户根据条件搜素商品
     *
     * @param goodsName:String 商品的名字
     * @param page:Integer     第几页
     * @param limit:Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(不可获取下架商品)
     */
    public Object listGoodsForSaleByCondition(String goodsName, Integer page, Integer limit, HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            if ("".equals(goodsName)) {
                goodsName = null;
            }
            List<GoodsPo> retGoodsList = goodsInfoService.listGoodsForSaleByCondition(goodsName, page, limit);
            Object retObj = ResponseUtil.ok(retGoodsList);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(776, "分页参数错误，获取商品列表失败");
            return retObj;
        }
    }

    /**
     * 3.用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id:Integer
     * @param page:Integer  第几页
     * @param limit:Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    public Object listGoodsForSaleByCategoryId(Integer id, Integer page, Integer limit, HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            GoodsCategoryPo goodsCategory = goodsInfoService.getGoodsCategoryById(id);
            if (goodsCategory != null) {
                List<GoodsPo> retGoodsList = goodsInfoService.listGoodsForSaleByCategoryId(id, page, limit);
                Object retObj = ResponseUtil.ok(retGoodsList);
                return retObj;
            } else {
                Object retObj = ResponseUtil.fail(776, "该分类不存在，获取商品列表失败");
                return retObj;
            }
        } else {
            Object retObj = ResponseUtil.fail(776, "分页参数错误，获取商品列表失败");
            return retObj;
        }
    }

    /**
     * 4.用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id:Integer
     * @param page:      Integer 第几页
     * @param limit:     Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    public Object listGoodsForSaleByBrandId(Integer id, Integer page, Integer limit, HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            BrandPo brand = goodsInfoService.getBrandById(id);
            if (brand != null) {
                List<GoodsPo> retGoodsList = goodsInfoService.listGoodsForSaleByBrandId(id, page, limit);
                Object retObj = ResponseUtil.ok(retGoodsList);
                return retObj;
            } else {
                Object retObj = ResponseUtil.fail(776, "该品牌不存在，获取商品列表失败");
                return retObj;
            }
        } else {
            Object retObj = ResponseUtil.fail(776, "分页参数错误，获取商品列表失败");
            return retObj;
        }
    }

    /**
     * 1.判断商品是否在售（内部接口）
     *
     * @param id：Integer
     * @return Boolean
     */
    public Object isGoodsOnSale(Integer id) {
        GoodsPo goods = goodsInfoService.getGoodsById(id);
        if (goods != null) {
            if (goods.getStatusCode() != 0) {
                Object retObj = ResponseUtil.ok(true);
                return retObj;
            } else {
                Object retObj = ResponseUtil.ok(false);
                return retObj;
            }
        } else {
            Object retObj = ResponseUtil.fail(775, "该商品不存在");
            return retObj;
        }
    }

    /**
     * 2.根据id搜索商品(内部接口)
     *
     * @param id：Integer
     * @return GoodsPo（不可获取下架商品）
     */
    public Object getGoodsInnerById(Integer id) {
        GoodsPo retGoods = goodsInfoService.getGoodsInnerById(id);
        if (retGoods != null) {
            Object retObj = ResponseUtil.ok(retGoods);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(775, "该商品不存在");
            return retObj;
        }
    }
}