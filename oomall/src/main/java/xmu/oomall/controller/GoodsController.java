package xmu.oomall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.feign.LogClientService;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.*;
import xmu.oomall.service.*;

import xmu.oomall.util.Copyer;
import xmu.oomall.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        Goods retGoods = goodsInfoService.getGoodsById(id);
        if (retGoods != null) {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询商品",1,null);
            //logClientService.addLog(log);
            retGoods.setBrandPo(goodsInfoService.getBrandById(retGoods.getBrandId()));
            retGoods.setGoodsCategoryPo(goodsInfoService.getGoodsCategoryById(retGoods.getGoodsCategoryId()));
            //goods.setGrouponRule();
            //goods.setPresaleRule();
            List<Product> listProducts = goodsInfoService.listProductsByGoodsId(id, 1, Integer.MAX_VALUE);
            List<ProductPo> listProductPos = new ArrayList<ProductPo>(listProducts.size());
            for (Product product : listProducts) {
                listProductPos.add(product);
            }
            retGoods.setProductPoList(listProductPos);
            //goods.setShareRule();

            Object retObj = ResponseUtil.ok(retGoods);
            return retObj;
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询商品",0,null);
            //logClientService.addLog(log);
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
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询商品",1,null);
            //logClientService.addLog(log);
            List<Goods> listGoods = goodsInfoService.listGoodsByCondition(goodsSn, goodsName, page, limit);
            List<GoodsPo> retListGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
            for (Goods goods : listGoods) {
                retListGoodsPo.add(goods);
            }
            Object retObj = ResponseUtil.ok(retListGoodsPo);
            return retObj;
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询商品",0,null);
            //logClientService.addLog(log);
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
    public Object addGoods(GoodsPo goodsPo,HttpServletRequest request) {
        if (goodsPo != null) {
            Goods goods = goodsConverter(goodsPo);
            Goods retGoods = goodsInfoService.addGoods(goods);
            if (retGoods != null) {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),1,"新建商品",1,null);
                //logClientService.addLog(log);
                Object retObj = ResponseUtil.ok(retGoods);
                return retObj;
            } else {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),1,"新建商品",0,null);
                //logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(771, "数据库操作失败,商品新建失败");
                return retObj;
            }
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),1,"新建商品",0,null);
            //logClientService.addLog(log);
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
    public Object updateGoodsById(Integer id, GoodsPo goodsPo,HttpServletRequest request) {
        if (goodsPo != null) {
            goodsPo.setId(id);
            Goods goods = goodsInfoService.getGoodsById(id);
            if (goods != null) {
                Goods goods1 = goodsConverter(goodsPo);
                Goods retGoods = goodsInfoService.updateGoodsById(goods,goods1);
                if (retGoods != null) {
                    Log log=new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"),2,"修改商品",1,null);
                    //logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok(retGoods);
                    return retObj;
                } else {
                    Log log=new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"),2,"修改商品",0,null);
                    //logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(772, "数据库操作失败,商品修改失败");
                    return retObj;
                }
            } else {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),2,"修改商品",0,null);
                //logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(772, "该商品不存在,商品修改失败");
                return retObj;
            }
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),2,"修改商品",0,null);
            //logClientService.addLog(log);
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
    public Object deleteGoodsById(Integer id,HttpServletRequest request) {
        Goods goods = goodsInfoService.getGoodsById(id);
        if (goods != null) {
            boolean ret = goodsInfoService.deleteGoodsById(goods);
            if (ret) {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),3,"删除商品",1,null);
                //logClientService.addLog(log);
                Object retObj = ResponseUtil.ok();
                return retObj;
            } else {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),3,"删除商品",0,null);
                //logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(773, "数据库操作失败,商品删除失败");
                return retObj;
            }
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),3,"删除商品",0,null);
            //logClientService.addLog(log);
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
    public Object getGoodsForSaleById(Integer id) {
        Goods retGoods = goodsInfoService.getGoodsForSaleById(id);
        if (retGoods != null) {
            retGoods.setBrandPo(goodsInfoService.getBrandById(retGoods.getBrandId()));
            retGoods.setGoodsCategoryPo(goodsInfoService.getGoodsCategoryById(retGoods.getGoodsCategoryId()));
            //goods.setGrouponRule();
            //goods.setPresaleRule();
            List<Product> listProducts = goodsInfoService.listProductsByGoodsId(id, 1, Integer.MAX_VALUE);
            List<ProductPo> listProductPos = new ArrayList<ProductPo>(listProducts.size());
            for (Product product : listProducts) {
                listProductPos.add(product);
            }
            retGoods.setProductPoList(listProductPos);
            //goods.setShareRule();

            Object retObj = ResponseUtil.ok(retGoods);
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
    public Object listGoodsForSaleByCondition(String goodsName, Integer page, Integer limit) {
        if (page > 0 && limit > 0) {
            List<Goods> listGoods = goodsInfoService.listGoodsForSaleByCondition(goodsName, page, limit);
            List<GoodsPo> retListGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
            for (Goods goods : listGoods) {
                retListGoodsPo.add(goods);
            }
            Object retObj = ResponseUtil.ok(retListGoodsPo);
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
    public Object listGoodsForSaleByCategoryId(Integer id, Integer page, Integer limit) {
        if (page > 0 && limit > 0) {
            GoodsCategory goodsCategory = goodsInfoService.getGoodsCategoryById(id);
            if (goodsCategory != null) {
                List<Goods> listGoods = goodsInfoService.listGoodsForSaleByCategoryId(id, page, limit);
                List<GoodsPo> retListGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
                for (Goods goods : listGoods) {
                    retListGoodsPo.add(goods);
                }
                Object retObj = ResponseUtil.ok(retListGoodsPo);
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
    public Object listGoodsForSaleByBrandId(Integer id, Integer page, Integer limit) {
        if (page > 0 && limit > 0) {
            Brand brand = goodsInfoService.getBrandById(id);
            if (brand != null) {
                List<Goods> listGoods = goodsInfoService.listGoodsForSaleByBrandId(id, page, limit);
                List<GoodsPo> retListGoodsPo = new ArrayList<GoodsPo>(listGoods.size());
                for (Goods goods1 : listGoods) {
                    retListGoodsPo.add(goods1);
                }
                Object retObj = ResponseUtil.ok(retListGoodsPo);
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
        Goods goods = goodsInfoService.getGoodsById(id);
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
        Goods retGoods = goodsInfoService.getGoodsInnerById(id);
        if (retGoods != null) {
            Object retObj = ResponseUtil.ok(retGoods);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(775, "该商品不存在");
            return retObj;
        }
    }

    /**
     * 将GoodsPo转换成Goods对象
     */
    private Goods goodsConverter(GoodsPo goodsPo) {
        Goods goods = new Goods();
        return Copyer.Copy(goodsPo, goods) ? goods : null;
    }
}