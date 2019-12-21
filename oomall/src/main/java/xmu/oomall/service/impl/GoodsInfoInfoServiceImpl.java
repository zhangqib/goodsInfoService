package xmu.oomall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.dao.*;
import xmu.oomall.domain.*;
import xmu.oomall.service.GoodsInfoService;

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
     * @return Goods(可获取下架商品)
     */
    @Override
    public Goods getGoodsById(Integer id) {
        return goodsDao.selectById(id);
    }

    /**
     * 2.管理员根据条件搜索商品
     *
     * @param goodsSn   :String   商品的序列号
     * @param goodsName :String 商品的名字
     * @param page      :            Integer 第几页
     * @param limit     :           Integer 一页多少
     * @return List<Goods>,搜索到的商品的列表(可获取下架商品)
     */
    @Override
    public List<Goods> listGoodsByCondition(String goodsSn, String goodsName, Integer page, Integer limit) {
        return goodsDao.selectByCondition(goodsSn, goodsName, null, page, limit);
    }

    /**
     * 3.管理员新建商品
     *
     * @param goods ：Goods
     * @return Goods，新建的商品
     */
    @Override
    public Goods addGoods(Goods goods) {
        return goodsDao.insert(goods);
    }

    /**
     * 4.管理员根据id修改商品
     *
     * @param goods :Goods
     * @return Goods，修改后的商品
     */
    @Override
    public Goods updateGoodsById(Goods goods) {
        Goods goods1 = goodsDao.selectById(goods.getId());
        if (goods1 != null) {
            if (goods1.getStatusCode() != 0
                    && goods.getStatusCode() == 0) {
                //去其他服务查看活动是否下线
                //如果活动下线则下架 否则失败
                if (true) {
                    return goodsDao.updateById(goods);
                }
            }
        }
        return null;
    }

    /**
     * 5.管理员根据id删除商品
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public boolean deleteGoodsById(Integer id) {
        Goods goods = goodsDao.selectById(id);
        if (goods != null) {
            if (goods.getStatusCode() == 0) {
                boolean ret = goodsDao.deleteById(id);
                if (ret) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 1.用户根据id搜索商品
     *
     * @param id ：Integer
     * @return Goods（不可获取下架商品）
     */
    @Override
    public Goods getGoodsForSaleById(Integer id) {
        Goods goods = goodsDao.selectById(id);
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
     * 2.用户根据条件搜素商品
     *
     * @param goodsName :String 商品的名字
     * @param page      :Integer     第几页
     * @param limit     :Integer    一页多少
     * @return List<Goods>,搜索到的商品的列表(不可获取下架商品)
     */
    @Override
    public List<Goods> listGoodsForSaleByCondition(String goodsName, Integer page, Integer limit) {
        return goodsDao.selectForSaleByCondition(null, goodsName, page, limit);
    }

    /**
     * 3.用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id    :Integer
     * @param page  :Integer  第几页
     * @param limit :Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    @Override
    public List<Goods> listGoodsForSaleByCategoryId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectForSaleByCategoryId(id, page, limit);
    }

    /**
     * 4.用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id    :Integer
     * @param page  :      Integer 第几页
     * @param limit :     Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    @Override
    public List<Goods> listGoodsForSaleByBrandId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectForSaleByBrandId(id, page, limit);
    }

    /**
     * 1.判断商品是否在售（内部接口）
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Boolean isGoodsOnSale(Integer id) {
        Goods goods = goodsDao.selectById(id);
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
     * @param id ：Integer(PathVariable
     * @return Goods（不可获取下架商品）
     */
    @Override
    public Goods getGoodsInnerById(Integer id) {
        return goodsDao.selectById(id);
    }


    /**
     * 1.管理员搜索某个商品下的所有产品
     *
     * @param id    :Integer
     * @param page  :Integer  第几页
     * @param limit :Integer 一页多少
     * @return List<Product>，所属该商品的产品列表
     */
    @Override
    public List<Product> listProductsByGoodsId(Integer id, Integer page, Integer limit) {
        return productDao.selectByGoodsId(id, page, limit);
    }

    /**
     * 2.管理员新建某个商品下的产品
     *
     * @param product :Product
     * @return Product，新建的商品
     */
    @Override
    public Product addProduct(Product product) {
        return productDao.insert(product);
    }

    /**
     * 3.管理员根据id修改产品
     *
     * @param product :Product
     * @return Product，修改后的产品
     */
    @Override
    public Product updateProductById(Product product) {
        return productDao.updateById(product);
    }

    /**
     * 4.管理员根据id删除产品
     *
     * @param id :Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteProductById(Integer id) {
        return productDao.deleteById(id);
    }

    /**
     * 1.用户根据id搜索产品（内部接口）
     *
     * @param id :Integer
     * @return Product，搜索到的产品
     */
    @Override
    public Product getProductById(Integer id) {
        return productDao.selectById(id);
    }

    /**
     * 2.根据id修改Product的库存（内部接口）
     *
     * @param orderItemList ：List<OrderItem>(根据orderItemList来修改库存
     * @param operation     :boolean(true加库存，false减库存
     * @return Boolean ，修改成功与否
     */
    @Override
    public Boolean updateStockByProductId(List<OrderItem> orderItemList, boolean operation) {
        return null;
    }

    /**
     * 1.管理员根据id搜索品牌
     *
     * @param id :Integer
     * @return Brand
     */
    @Override
    public Brand getBrandById(Integer id) {
        return brandDao.selectById(id);
    }

    /**
     * 2.管理员根据条件搜索品牌
     *
     * @param brandId   :String   品牌的id
     * @param brandName :String 品牌的名字
     * @param page      :            Integer 第几页
     * @param limit     :           Integer 一页多少
     * @return List<Brand>,搜索到的品牌列表
     */
    @Override
    public List<Brand> listBrandsByCondition(String brandId, String brandName, Integer page, Integer limit) {
        return brandDao.selectBrandsByCondition(brandId, brandName, page, limit);
    }

    /**
     * 3.管理员创建品牌
     *
     * @param brand :Brand 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return Brand
     */
    @Override
    public Brand addBrand(Brand brand) {
        return brandDao.insert(brand);
    }

    /**
     * 4.管理员修改品牌
     *
     * @param brand ：Brand(body包含name、description、picURL(上传图片产生))
     * @return Brand
     */
    @Override
    public Brand updateBrandById(Brand brand) {
        return brandDao.updateById(brand);
    }

    /**
     * 5.管理员根据id删除品牌
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteBrandById(Integer id) {
        return brandDao.deleteById(id);
    }

    /**
     * 1.用户根据id搜索品牌
     *
     * @param id :Integer
     * @return Brand
     */
    @Override
    public Brand getBrandForUserById(Integer id) {
        return brandDao.selectById(id);
    }

    /**
     * 2.用户搜索所有品牌
     *
     * @param page  :  Integer 第几页
     * @param limit : Integer 一页多少
     * @return List<Brand>,搜索到的品牌列表
     */
    @Override
    public List<Brand> listBrandsByCondition(Integer page, Integer limit) {
        return brandDao.selectBrandsByCondition(null, null, page, limit);
    }

    /**
     * 1.管理员根据id搜索分类
     *
     * @param id ：Integer
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory getGoodsCategoryById(Integer id) {
        return goodsCategoryDao.selectById(id);
    }

    /**
     * 2.管理员搜索所有分类
     *
     * @param page  :  Integer 第几页
     * @param limit : Integer 一页多少
     * @return List<GoodsCategory>
     */
    @Override
    public List<GoodsCategory> listGoodsCategories(Integer page, Integer limit) {
        return goodsCategoryDao.selectGoodsCategoriesByCondition(page, limit);
    }

    /**
     * 3.管理员新建分类
     *
     * @param goodsCategory ：GoodsCategory(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryDao.insert(goodsCategory);
    }

    /**
     * 4.管理员修改分类
     *
     * @param goodsCategory ：GoodsCategory(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory updateGoodsCategoryById(GoodsCategory goodsCategory) {
        return goodsCategoryDao.updateById(goodsCategory);
    }

    /**
     * 5.管理员修改子分类在父分类下的位置
     *
     * @param goodsCategory ：GoodsCategory(body包含pid(只能修改pid不为空的项，且修改后pid不可为空))
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory updateGoodsCategoryPidById(GoodsCategory goodsCategory) {
        return goodsCategoryDao.updatePidById(goodsCategory);
    }

    /**
     * 6.管理员删除分类
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteGoodsCategoryById(Integer id) {
        return goodsCategoryDao.deleteById(id);
    }

    /**
     * 1.用户搜索所有一级分类
     *
     * @param page  :  Integer 第几页
     * @param limit : Integer 一页多少
     * @return List<GoodsCategory>
     */
    @Override
    public List<GoodsCategory> listOneLevelGoodsCategories(Integer page, Integer limit) {
        return goodsCategoryDao.selectOneLevelGoodsCategories(page, limit);
    }

    /**
     * 2.用户搜索某一级分类下的所有二级分类
     *
     * @param id    ：Integer
     * @param page  :      Integer 第几页
     * @param limit :     Integer 一页多少
     * @return List<GoodsCategory>
     */
    @Override
    public List<GoodsCategory> listSecondLevelGoodsCategoryById(Integer id, Integer page, Integer limit) {
        return goodsCategoryDao.selectSecondLevelGoodsCategories(id, page, limit);
    }
}
