package xmu.oomall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.feign.LogClientService;
import xmu.oomall.domain.GoodsCategory;
import xmu.oomall.domain.Log;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.service.GoodsInfoService;
import xmu.oomall.util.Copyer;
import xmu.oomall.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ke
 * @Description: GoodsCategoryController
 * @create 2019/12/20
 */

@Configuration
public class GoodsCategoryController {
    @Autowired
    private LogClientService logClientService;
    @Autowired
    private GoodsInfoService goodsInfoService;

    /**
     * 1.管理员根据id搜索分类
     *
     * @param id：Integer
     * @return GoodsCategoryPo
     */
    @GetMapping("/categories/{id}")
    public Object getGoodsCategoryById(Integer id, HttpServletRequest request) {
        GoodsCategory retGoodsCategory = goodsInfoService.getGoodsCategoryById(id);
        if (retGoodsCategory != null) {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询分类",1,null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.ok(retGoodsCategory);
            return retObj;
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询分类",0,null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(804, "该分类不存在");
            return retObj;
        }
    }

    /**
     * 2.管理员搜索所有分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories")
    public Object listGoodsCategories(Integer page, Integer limit,
                                      HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询分类",1,null);
            logClientService.addLog(log);
            List<GoodsCategory> goodsCategoryList = goodsInfoService.listGoodsCategories(page, limit);
            List<GoodsCategoryPo> retGoodsCategoryPoList = new ArrayList<GoodsCategoryPo>(goodsCategoryList.size());
            for (GoodsCategory goodsCategory : goodsCategoryList) {
                retGoodsCategoryPoList.add(goodsCategory);
            }
            Object retObj = ResponseUtil.ok(retGoodsCategoryPoList);
            return retObj;
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),0,"查询分类",0,null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(805, "分页参数错误，获取分类列表失败");
            return retObj;
        }
    }

    /**
     * 3.管理员新建分类
     *
     * @param goodsCategoryPo：GoodsCategoryPo(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @PostMapping("/categories")
    public Object addGoodsCategory(GoodsCategoryPo goodsCategoryPo,HttpServletRequest request) {
        if (goodsCategoryPo != null) {
            GoodsCategory goodsCategory = goodsCategoryConverter(goodsCategoryPo);
            GoodsCategory retGoodsCategory = goodsInfoService.addGoodsCategory(goodsCategory);
            if (retGoodsCategory != null) {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),1,"新建分类",1,null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.ok(retGoodsCategory);
                return retObj;
            } else {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),1,"新建分类",0,null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(801, "数据库操作失败,分类新建失败");
                return retObj;
            }
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),1,"新建分类",0,null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(801, "前端传入数据为null,分类新建失败");
            return retObj;
        }
    }

    /**
     * 4.管理员修改分类
     *
     * @param id：Integer
     * @param goodsCategoryPo：GoodsCategoryPo(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @PutMapping("/categories/{id}")
    public Object updateGoodsCategoryById(Integer id, GoodsCategoryPo goodsCategoryPo,HttpServletRequest request) {
        if (goodsCategoryPo != null) {
            goodsCategoryPo.setId(id);
            GoodsCategory goodsCategory = goodsInfoService.getGoodsCategoryById(id);
            if (goodsCategory != null) {
                GoodsCategory goodsCategory1 = goodsCategoryConverter(goodsCategoryPo);
                GoodsCategory retGoods = goodsInfoService.updateGoodsCategoryById(goodsCategory1);
                if (retGoods != null) {
                    Log log=new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"),2,"修改品牌",1,null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok(retGoods);
                    return retObj;
                } else {
                    Log log=new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"),2,"修改品牌",0,null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(802, "数据库操作失败,分类修改失败");
                    return retObj;
                }
            } else {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),2,"修改品牌",0,null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(802, "该分类不存在,分类修改失败");
                return retObj;
            }
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),2,"修改品牌",0,null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(802, "前端传入数据为null,分类修改失败");
            return retObj;
        }
    }

    /**
     * 5.管理员修改子分类在父分类下的位置
     *
     * @param id：Integer
     * @param goodsCategoryPo：GoodsCategoryPo(body包含pid(只能修改pid不为空的项，且修改后pid不可为空))
     * @return GoodsCategoryPo
     */
    @PutMapping("/categories/l2/{id}")
    public Object updateGoodsCategoryPidById(Integer id, GoodsCategoryPo goodsCategoryPo,HttpServletRequest request) {
        if (goodsCategoryPo != null) {
            goodsCategoryPo.setId(id);
            GoodsCategory goodsCategory = goodsInfoService.getGoodsCategoryById(id);
            if (goodsCategory != null) {
                GoodsCategory goodsCategory1 = goodsCategoryConverter(goodsCategoryPo);
                GoodsCategory retGoods = goodsInfoService.updateGoodsCategoryPidById(goodsCategory1);
                if (retGoods != null) {
                    Log log=new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"),2,"修改品牌",1,null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok(retGoods);
                    return retObj;
                } else {
                    Log log=new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"),2,"修改品牌",0,null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(802, "数据库操作失败,子分类修改失败");
                    return retObj;
                }
            } else {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),2,"修改品牌",0,null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(802, "该子分类不存在,子分类修改失败");
                return retObj;
            }
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),2,"修改品牌",0,null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(802, "前端传入数据为null,子分类修改失败");
            return retObj;
        }
    }

    /**
     * 6.管理员删除分类
     *
     * @param id：Integer
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    @DeleteMapping("/categories/{id}")
    public Object deleteGoodsCategoryById(Integer id,HttpServletRequest request) {
        GoodsCategory goodsCategory = goodsInfoService.getGoodsCategoryById(id);
        if (goodsCategory != null) {
            boolean ret = goodsInfoService.deleteGoodsCategoryById(id);
            if (ret) {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),3,"删除分类",1,null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.ok();
                return retObj;
            } else {
                Log log=new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"),3,"删除分类",0,null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(803, "数据库操作失败,分类删除失败");
                return retObj;
            }
        } else {
            Log log=new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"),3,"删除分类",0,null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(803, "该分类不存在,分类删除失败");
            return retObj;
        }
    }

    /**
     * 1.用户搜索所有一级分类
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @GetMapping("/categories/l1")
    public Object listOneLevelGoodsCategories(Integer page, Integer limit) {
        if (page > 0 && limit > 0) {
            List<GoodsCategory> goodsCategoryList = goodsInfoService.listOneLevelGoodsCategories(page, limit);
            List<GoodsCategoryPo> retGoodsCategoryPoList = new ArrayList<GoodsCategoryPo>(goodsCategoryList.size());
            for (GoodsCategory goodsCategory : goodsCategoryList) {
                retGoodsCategoryPoList.add(goodsCategory);
            }
            Object retObj = ResponseUtil.ok(retGoodsCategoryPoList);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(805, "分页参数错误，获取所有一级分类列表失败");
            return retObj;
        }
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
    public Object listSecondLevelGoodsCategoryById(Integer id, Integer page, Integer limit) {
        if (page > 0 && limit > 0) {
            GoodsCategory goodsCategory = goodsInfoService.getGoodsCategoryById(id);
            if (goodsCategory != null) {
                List<GoodsCategory> goodsCategoryList = goodsInfoService.listSecondLevelGoodsCategoryById(id, page, limit);
                List<GoodsCategoryPo> retGoodsCategoryPoList = new ArrayList<GoodsCategoryPo>(goodsCategoryList.size());
                for (GoodsCategory goodsCategory1 : goodsCategoryList) {
                    retGoodsCategoryPoList.add(goodsCategory1);
                }
                Object retObj = ResponseUtil.ok(retGoodsCategoryPoList);
                return retObj;
            } else {
                Object retObj = ResponseUtil.fail(805, "该一级分类不存在，获取该一级分类下的所有二级分类列表失败");
                return retObj;
            }
        } else {
            Object retObj = ResponseUtil.fail(805, "分页参数错误，获取该一级分类下的所有二级分类列表失败");
            return retObj;
        }
    }

    /**
     * 将GoodsCategoryPo转换成GoodsCategory对象
     */
    private GoodsCategory goodsCategoryConverter(GoodsCategoryPo goodsCategoryPo) {
        GoodsCategory goodsCategory = new GoodsCategory();
        return Copyer.Copy(goodsCategoryPo, goodsCategory) ? goodsCategory : null;
    }
}