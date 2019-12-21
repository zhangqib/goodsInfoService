package xmu.oomall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.feign.LogClientService;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.service.GoodsInfoService;
import xmu.oomall.util.Copyer;
import xmu.oomall.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ke
 * @Description: ProductController
 * @create 2019/12/20
 */

@Component
public class ProductController {
    @Autowired
    private LogClientService logClientService;
    @Autowired
    private GoodsInfoService goodsInfoService;

    /**
     * 1.管理员搜索某个商品下的所有产品
     *
     * @param id:Integer
     * @param page:Integer  第几页
     * @param limit:Integer 一页多少
     * @return List<ProductPo>，所属该商品的产品列表
     */
    @GetMapping("/goods/{id}/products")
    public Object listProductsByGoodsId(Integer id, Integer page, Integer limit, HttpServletRequest request) {
        if (page > 0 && limit > 0) {
            Goods goods = goodsInfoService.getGoodsById(id);
            if (goods != null) {
                List<Product> productList = goodsInfoService.listProductsByGoodsId(id, page, limit);
                List<ProductPo> retProductPoList = new ArrayList<ProductPo>(productList.size());
                for (Product product : productList) {
                    retProductPoList.add(product);
                }
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 0, "查询产品", 1, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.ok(retProductPoList);
                return retObj;
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 0, "查询产品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(785, "该商品不存在,获取产品列表失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 0, "查询产品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(785, "分页参数错误,获取产品列表失败");
            return retObj;
        }
    }

    /**
     * 2.管理员新建某个商品下的产品
     *
     * @param id:Integer
     * @param productPo:ProductPo
     * @return ProductPo，新建的商品
     */
    @PostMapping("/goods/{id}/products")
    public Object addProduct(Integer id, ProductPo productPo, HttpServletRequest request) {
        if (productPo != null) {
            productPo.setGoodsId(id);
            Goods goods = goodsInfoService.getGoodsById(id);
            if (goods != null) {
                Product product = productConverter(productPo);
                Product retProduct = goodsInfoService.addProduct(product);
                if (retProduct != null) {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 1, "修改产品", 1, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok(retProduct);
                    return retObj;
                } else {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 1, "修改产品", 0, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(781, "数据库操作失败,产品新建失败");
                    return retObj;
                }
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 1, "修改产品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(781, "该商品不存在,产品新建失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 1, "修改产品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(781, "前端传入数据为null,产品新建失败");
            return retObj;
        }
    }

    /**
     * 3.管理员根据id修改产品
     *
     * @param id:Integer
     * @param productPo:ProductPo
     * @return ProductPo，修改后的产品
     */
    @PutMapping("/products/{id}")
    public Object updateProductById(Integer id, ProductPo productPo, HttpServletRequest request) {
        if (productPo != null) {
            productPo.setId(id);
            Product product = goodsInfoService.getProductById(id);
            if (product != null) {
                Product product1 = productConverter(productPo);
                Product retProduct = goodsInfoService.updateProductById(product1);
                if (retProduct != null) {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 2, "修改产品", 1, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok(retProduct);
                    return retObj;
                } else {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 2, "修改产品", 0, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(782, "数据库操作失败,产品修改失败");
                    return retObj;
                }
            } else {
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 2, "修改产品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(782, "该产品不存在,产品修改失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 2, "修改产品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(782, "前端传入数据为null,产品修改失败");
            return retObj;
        }
    }

    /**
     * 4.管理员根据id删除产品
     *
     * @param id:Integer
     * @return ResponseUtil.ok()或者ResponseUtil.fail()
     */
    @DeleteMapping("/products/{id}")
    public Object deleteProductById(Integer id, HttpServletRequest request) {
        Product product = goodsInfoService.getProductById(id);
        if (product != null) {
            if (!product.getBeDeleted()) {
                boolean ret = goodsInfoService.deleteProductById(product);
                if (ret) {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 3, "删除产品", 1, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.ok();
                    return retObj;
                } else {
                    Log log = new Log(request.getIntHeader("userId"),
                            request.getHeader("ip"), 3, "删除产品", 0, null);
                    logClientService.addLog(log);
                    Object retObj = ResponseUtil.fail(783, "数据库操作失败,产品删除失败");
                    return retObj;
                }
            }else{
                Log log = new Log(request.getIntHeader("userId"),
                        request.getHeader("ip"), 3, "删除产品", 0, null);
                logClientService.addLog(log);
                Object retObj = ResponseUtil.fail(783, "该产品已删除,产品删除失败");
                return retObj;
            }
        } else {
            Log log = new Log(request.getIntHeader("userId"),
                    request.getHeader("ip"), 3, "删除产品", 0, null);
            logClientService.addLog(log);
            Object retObj = ResponseUtil.fail(783, "该产品不存在,产品删除失败");
            return retObj;
        }
    }

    /**
     * 1.用户根据id搜索产品（内部接口）
     *
     * @param id:Integer
     * @return Product，搜索到的产品
     */
    @GetMapping("/user/product/{id}")
    public Object getProductById(Integer id) {
        Product retProduct = goodsInfoService.getProductById(id);
        if (retProduct != null) {
            Object retObj = ResponseUtil.ok(retProduct);
            return retObj;
        } else {
            Object retObj = ResponseUtil.fail(784, "该产品不存在");
            return retObj;
        }
    }

    /**
     * 2.根据id修改Product的库存（内部接口）
     *
     * @param orderItemList：List<OrderItem>(根据orderItemList来修改库存
     * @param operation:boolean(true加库存，false减库存
     * @return Boolean ，修改成功与否
     */
    @PutMapping("/product/list/deduct")
    public Object updateStockByProductId(List<OrderItem> orderItemList, boolean operation) {
        if (orderItemList != null) {
            boolean ret = goodsInfoService.updateStockByProductId(orderItemList, operation);
            if (ret) {
                Object retObj = ResponseUtil.ok(true);
                return retObj;
            } else {
                Object retObj = ResponseUtil.fail(786, "数据库操作失败,修改产品库存失败");
                return retObj;
            }
        } else {
            Object retObj = ResponseUtil.fail(786, "前端传入数据为null,修改产品库存失败");
            return retObj;
        }
    }

    /**
     * 将ProductPo转换成Product对象
     */
    private Product productConverter(ProductPo productPo) {
        Product product = new Product();
        return Copyer.Copy(productPo, product) ? product : null;
    }
}