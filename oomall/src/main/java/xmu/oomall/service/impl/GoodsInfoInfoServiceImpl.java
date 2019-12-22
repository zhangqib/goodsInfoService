package xmu.oomall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.dao.*;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.service.GoodsInfoService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Ke
 * @Description: GoodsServiceImpl
 * @create 2019/12/15 18:22
 */

@Service
public class GoodsInfoInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsDAO goodsDao;
    @Autowired
    private ProductDAO productDao;
    @Autowired
    private BrandDAO brandDao;
    @Autowired
    private GoodsCategoryDAO goodsCategoryDao;

    /**
     * 1.管理员根据id搜索商品
     *
     * @param id ：Integer
     * @return GoodsPo(可获取下架商品)
     */
    @Override
    public GoodsPo getGoodsById(Integer id) {
        return goodsDao.selectById(id);
    }

    /**
     * 2.管理员根据条件搜索商品
     *
     * @param goodsSn   :String   商品的序列号
     * @param goodsName :String 商品的名字
     * @param page      :            Integer 第几页
     * @param limit     :           Integer 一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(可获取下架商品)
     */
    @Override
    public List<GoodsPo> listGoodsByCondition(String goodsSn, String goodsName, Integer page, Integer limit) {
        return goodsDao.selectByCondition(goodsSn, goodsName, null, page, limit);
    }

    /**
     * 3.管理员新建商品
     *
     * @param goods ：GoodsPo
     * @return GoodsPo，新建的商品
     */
    @Override
    public GoodsPo addGoods(GoodsPo goods) {
        return goodsDao.insert(goods);
    }

    /**
     * 4.管理员根据id修改商品
     *
     * @param goodsOld
     * @param goodsNew
     * @return GoodsPo，修改后的商品
     */
    @Override
    public GoodsPo updateGoodsById(GoodsPo goodsOld, GoodsPo goodsNew) {
        if (goodsOld.getStatusCode() != 0 && goodsNew.getStatusCode() == 0) {
            //去其他服务看该商品的活动是否下线
            return goodsDao.updateById(goodsNew);
        } else {
            return goodsDao.updateById(goodsNew);
        }
    }

    /**
     * 5.管理员根据id删除商品
     *
     * @param goods
     * @return boolean
     */
    @Override
    public Integer deleteGoodsById(GoodsPo goods) {
        if (goods != null) {
            if (goods.getStatusCode() == 0) {
                boolean ret = goodsDao.deleteById(goods.getId());
                if (ret) {

                    return 1;
                }
            } else {
                return 0;
            }
        }
        return -1;
    }

    /**
     * 1.用户根据id搜索商品
     *
     * @param id ：Integer
     * @return Goods（不可获取下架商品）
     */
    @Override
    public GoodsPo getGoodsForSaleById(Integer id) {
        GoodsPo goods = goodsDao.selectById(id);
        if (goods != null) {
            if (goods.getStatusCode() > 0) {
                //1.获取userId
                //2.有的话调用足迹服务（@PostMapping("/footprints") ），没有的话跳过
                return goods;
            }
        }
        return null;
    }

    /**
     * 2.用户根据条件搜素商品
     *
     * @param goodsName :String 商品的名字
     * @param page      :Integer     第几页
     * @param limit     :Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表(不可获取下架商品)
     */
    @Override
    public List<GoodsPo> listGoodsForSaleByCondition(String goodsName, Integer page, Integer limit) {
        return goodsDao.selectForSaleByCondition(null, goodsName, page, limit);
    }

    /**
     * 3.用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id    :Integer
     * @param page  :Integer  第几页
     * @param limit :Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @Override
    public List<GoodsPo> listGoodsForSaleByCategoryId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectForSaleByCategoryId(id, page, limit);
    }

    /**
     * 4.用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id    :Integer
     * @param page  :      Integer 第几页
     * @param limit :     Integer 一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @Override
    public List<GoodsPo> listGoodsForSaleByBrandId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectForSaleByBrandId(id, page, limit);
    }

    /**
     * 1.判断商品是否在售（内部接口）
     *
     * @param id ：Integer
     * @return boolean
     */
    @Override
    public boolean isGoodsOnSale(Integer id) {
        GoodsPo goods = goodsDao.selectById(id);
        if (goods != null) {
            if (goods.getStatusCode() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 2.根据id搜索商品(内部接口)
     *
     * @param id ：Integer
     * @return GoodsPo（不可获取下架商品）
     */
    @Override
    public GoodsPo getGoodsInnerById(Integer id) {
        GoodsPo goods = goodsDao.selectById(id);
        if (goods != null) {
            if (goods.getStatusCode() != 0) {
                //1.获取userId
                //2.有的话调用足迹服务（@PostMapping("/footprints") ），没有的话跳过
                return goods;
            }
        }
        return null;
    }

    /**
     * 1.管理员搜索某个商品下的所有产品
     *
     * @param id    :Integer
     * @param page  :Integer  第几页
     * @param limit :Integer 一页多少
     * @return List<ProductPo>，所属该商品的产品列表
     */
    @Override
    public List<ProductPo> listProductsByGoodsId(Integer id, Integer page, Integer limit) {
        return productDao.selectByGoodsId(id, page, limit);
    }

    /**
     * 2.管理员新建某个商品下的产品
     *
     * @param product :ProductPo
     * @return ProductPo，新建的商品
     */
    @Override
    public ProductPo addProduct(ProductPo product) {
        return productDao.insert(product);
    }

    /**
     * 3.管理员根据id修改产品
     *
     * @param product :ProductPo
     * @return ProductPo，修改后的产品
     */
    @Override
    public ProductPo updateProductById(ProductPo product) {
        return productDao.updateById(product);
    }

    /**
     * 4.管理员根据id删除产品
     *
     * @param product:ProductPo
     * @return boolean
     */
    @Override
    public boolean deleteProductById(ProductPo product) {
        if (product != null) {
                boolean ret = productDao.deleteById(product);
                if (ret) {
                    return true;
                }
        }
        return false;
    }

    private Boolean pullOffGoods(GoodsPo goods) {
        GoodsPo goodsNew = new GoodsPo(goods);
        goodsNew.setStatusCode(0);
        GoodsPo retGoods = updateGoodsById(goods, goodsNew);
        if (retGoods != null) {
            return true;
        }
        return false;
    }

    /**
     * 1.用户根据id搜索产品（内部接口）
     *
     * @param id :Integer
     * @return Product，搜索到的产品
     */
    @Override
    public ProductPo getProductById(Integer id) {
        return productDao.selectById(id);
    }

    /**
     * 2.根据id修改Product的库存（内部接口）
     *
     * @param orderItemList ：List<OrderItem>(根据orderItemList来修改库存
     * @param operation     :boolean(true加库存，false减库存
     * @return boolean ，修改成功与否
     */
    @Override
    public boolean updateStockByProductId(List<OrderItem> orderItemList, boolean operation) {
        boolean flag=true;
        if (operation) {
            for (OrderItem orderItem : orderItemList) {
                boolean ret = productDao.descStock(orderItem.getProductId(), -orderItem.getNumber());
                if (ret) {
                    return true;
                }
            }
        } else {
            for (OrderItem orderItem : orderItemList) {
                boolean ret = productDao.descStock(orderItem.getProductId(), orderItem.getNumber());
                if (ret) {
                    flag=false;
                }
            }
            if(flag){
                return true;
            }
        }
        return false;
    }

    /**
     * 1.管理员根据id搜索品牌
     *
     * @param id :Integer
     * @return BrandPo
     */
    @Override
    public BrandPo getBrandById(Integer id) {
        return brandDao.selectById(id);
    }

    /**
     * 2.管理员根据条件搜索品牌
     *
     * @param brandId   :String   品牌的id
     * @param brandName :String 品牌的名字
     * @param page      :            Integer 第几页
     * @param limit     :           Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @Override
    public List<BrandPo> listBrandsByCondition(Integer brandId, String brandName, Integer page, Integer limit) {
        return brandDao.selectBrandsByCondition(brandId, brandName, page, limit);
    }

    /**
     * 3.管理员创建品牌
     *
     * @param brand :BrandPo 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @Override
    public BrandPo addBrand(BrandPo brand) {
        return brandDao.insert(brand);
    }

    /**
     * 4.管理员修改品牌
     *
     * @param brand ：BrandPo(body包含name、description、picURL(上传图片产生))
     * @return BrandPo
     */
    @Override
    public BrandPo updateBrandById(BrandPo brand) {
        return brandDao.updateById(brand);
    }

    /**
     * 5.管理员根据id删除品牌
     *
     * @param id ：Integer
     * @return boolean
     */
    @Override
    public boolean deleteBrandById(Integer id) {
        return brandDao.deleteById(id);
    }

    /**
     * 1.用户根据id搜索品牌
     *
     * @param id :Integer
     * @return BrandPo
     */
    @Override
    public BrandPo getBrandForUserById(Integer id) {
        return brandDao.selectById(id);
    }

    /**
     * 2.用户搜索所有品牌
     *
     * @param page  :  Integer 第几页
     * @param limit : Integer 一页多少
     * @return List<BrandPo>,搜索到的品牌列表
     */
    @Override
    public List<BrandPo> listBrandsByCondition(Integer page, Integer limit) {
        return brandDao.selectAll(page, limit);
    }

    /**
     * 1.管理员根据id搜索分类
     *
     * @param id ：Integer
     * @return GoodsCategoryPo
     */
    @Override
    public GoodsCategoryPo getGoodsCategoryById(Integer id) {
        return goodsCategoryDao.selectById(id);
    }

    /**
     * 2.管理员搜索所有分类
     *
     * @param page  :  Integer 第几页
     * @param limit : Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @Override
    public List<GoodsCategoryPo> listGoodsCategories(Integer page, Integer limit) {
        return goodsCategoryDao.selectGoodsCategoriesByCondition(page, limit);
    }

    /**
     * 3.管理员新建分类
     *
     * @param goodsCategory ：GoodsCategoryPo(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @Override
    public GoodsCategoryPo addGoodsCategory(GoodsCategoryPo goodsCategory) {
        return goodsCategoryDao.insert(goodsCategory);
    }

    /**
     * 4.管理员修改分类
     *
     * @param goodsCategory ：GoodsCategoryPo(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategoryPo
     */
    @Override
    public GoodsCategoryPo updateGoodsCategoryById(GoodsCategoryPo goodsCategory) {
        return goodsCategoryDao.updateById(goodsCategory);
    }

    /**
     * 5.管理员修改子分类在父分类下的位置
     *
     * @param goodsCategory ：GoodsCategoryPo(body包含pid(只能修改pid不为空的项，且修改后pid不可为空))
     * @return GoodsCategoryPo
     */
    @Override
    public GoodsCategoryPo updateGoodsCategoryPidById(GoodsCategoryPo goodsCategory) {
        return goodsCategoryDao.updatePidById(goodsCategory);
    }

    /**
     * 6.管理员删除分类
     *
     * @param id ：Integer
     * @return boolean
     */
    @Override
    public boolean deleteGoodsCategoryById(Integer id) {
        return goodsCategoryDao.deleteById(id);
    }

    /**
     * 1.用户搜索所有一级分类
     *
     * @param page  :  Integer 第几页
     * @param limit : Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @Override
    public List<GoodsCategoryPo> listOneLevelGoodsCategories(Integer page, Integer limit) {
        return goodsCategoryDao.selectOneLevelGoodsCategories(page, limit);
    }

    /**
     * 2.用户搜索某一级分类下的所有二级分类
     *
     * @param id    ：Integer
     * @param page  :      Integer 第几页
     * @param limit :     Integer 一页多少
     * @return List<GoodsCategoryPo>
     */
    @Override
    public List<GoodsCategoryPo> listSecondLevelGoodsCategoryById(Integer id, Integer page, Integer limit) {
        return goodsCategoryDao.selectSecondLevelGoodsCategories(id, page, limit);
    }
}
