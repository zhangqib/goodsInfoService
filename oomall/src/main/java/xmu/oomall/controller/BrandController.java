package xmu.oomall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.feign.LogClientService;
import xmu.oomall.domain.Log;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.service.GoodsInfoService;
import xmu.oomall.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Ke
 * @Description: BrandController
 * @create 2019/12/20
 */

@Component
public class BrandController {
    @Autowired
    private LogClientService logClientService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    //-----------------Brand---------------Brand-----------Brand---------

    /**
     * 1.管理员根据id搜索品牌
     *
     * @param id:Integer
     * @return BrandPo
     */
    @GetMapping("/admin/brands/{id}")
    public Object getBrandById(Integer id, HttpServletRequest request) {
        BrandPo retBrand = goodsInfoService.getBrandById(id);
        if (retBrand != null) {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询品牌", 1, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.ok(retBrand);
            return retObj;
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询品牌", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(794, "该品牌不存在");
            return retObj;
        }
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
    public Object listBrandsByCondition(String brandId, String brandName, Integer page, Integer limit,
                                        HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询品牌", 1, null);
            logClientService.addLog(log);
            Integer brandId2;
            if ("".equals(brandId)) {
                brandId2 = null;
            } else {
                brandId2 = Integer.parseInt(brandId);
            }
            if ("".equals(brandName)) {
                brandName = null;
            }
            List<BrandPo> retBrandList = goodsInfoService.listBrandsByCondition(brandId2, brandName, page, limit);
            Object retObj = ResponseUtil.ok(retBrandList);
            return retObj;
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询品牌", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(795, "分页参数错误，获取品牌列表失败");
            return retObj;
        }
    }

    /**
     * 3.管理员创建品牌
     *
     * @param brandPo:BrandPo 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @PostMapping("/brands")
    public Object addBrand(BrandPo brandPo, HttpServletRequest request) {
        if (brandPo != null) {
            BrandPo retBrand = goodsInfoService.addBrand(brandPo);
            if (retBrand != null) {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 1, "创建品牌", 1, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.ok(retBrand);
                return retObj;
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 1, "创建品牌", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(791, "数据库操作失败,品牌新建失败");
                return retObj;
            }
        }
        Log log = new Log(request.getIntHeader("userId"),
                request.getHeader("ip"), 1, "创建品牌", 0, null);
        logClientService.addLog(log);
        Object retObj = ResponseUtil.fail(791, "前端传入的数据为null,品牌新建失败");
        return retObj;
    }

    /**
     * 4.管理员修改品牌
     *
     * @param id：Integer
     * @param brandPo：BrandPo)(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @PutMapping("/brands/{id}")
    public Object updateBrandById(Integer id, BrandPo brandPo, HttpServletRequest request) {
        if (brandPo != null) {
            BrandPo brand = goodsInfoService.getBrandById(id);
            if (brand != null) {
                brandPo.setId(id);
                BrandPo retBrand = goodsInfoService.updateBrandById(brandPo);
                if (retBrand != null) {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 0, "修改品牌", 1, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok(retBrand);
                    return retObj;
                } else {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 2, "修改品牌", 0, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(792, "数据库操作失败,品牌修改失败");
                    return retObj;
                }
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 2, "修改品牌", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(792, "该品牌不存在,品牌修改失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 2, "修改品牌", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(792, "前端传入的数据为null,品牌修改失败");
            return retObj;
        }
    }

    /**
     * 5.管理员根据id删除品牌
     *
     * @param id：Integer
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    @DeleteMapping("/brands/{id}")
    public Object deleteBrandById(Integer id, HttpServletRequest request) {
        BrandPo brand = goodsInfoService.getBrandById(id);
        if (brand != null) {
            boolean ret = goodsInfoService.deleteBrandById(id);
            if (ret) {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 0, "删除品牌", 1, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.ok();
                return retObj;
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 3, "删除品牌", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(793, "数据库操作失败,品牌删除失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 3, "删除品牌", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(794, "该品牌不存在,品牌删除失败");
            return retObj;
        }
    }

    /**
     * 1.用户根据id搜索品牌
     *
     * @param id:Integer
     * @return BrandPo
     */
    @GetMapping("/brands/{id}")
    public Object getBrandForUserById(Integer id, HttpServletRequest request) {
        BrandPo retBrand = goodsInfoService.getBrandById(id);
        if (retBrand != null) {
            Object retObj = ResponseUtil.ok(retBrand);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(794, "该品牌不存在");
            return retObj;
        }
    }

    /**
     * 2.用户搜索所有品牌
     *
     * @param page:  Integer 第几页
     * @param limit: Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @GetMapping("/brands")
    public Object listBrandsByCondition(Integer page, Integer limit, HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            List<BrandPo> retBrandList = goodsInfoService.listBrandsByCondition(page, limit);
            Object retObj = ResponseUtil.ok(retBrandList);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(795, "分页参数错误，获取产品列表失败");
            return retObj;
        }
    }
}