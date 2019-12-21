package xmu.oomall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.dao.*;
import xmu.oomall.domain.*;
import xmu.oomall.service.GoodsService;

import java.util.List;

/**
 * @Author Ke
 * @Description: GoodsServiceImpl
 * @create 2019/12/15 18:22
 */

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDAO goodsDao;
    @Autowired
    private ProductDAO productDao;
    @Autowired
    private BrandDAO brandDao;
    @Autowired
    private GoodsCategoryDAO goodsCategoryDao;

    /**
     * 管理员根据id搜索商品
     *
     * @param id ：Integer
     * @return Goods(可获取下架商品)
     */
    @Override
    public Goods getGoodsById(Integer id) {
        return goodsDao.selectById(id);
    }

    /**
     * 用户根据id搜索商品
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

//    /**
//     * 管理员根据id搜索商品的特殊运费规则
//     *
//     * @param id ：Integer
//     * @return SpecialFreight（可获取下架商品）
//     */
//    @Override
//    public SpecialFreight getSpecialFreightsByGoodsId(Integer id) {
//        Goods goods = goodsDao.selectById(id);
//        if (goods != null) {
//            //调用运费服务
//        }
//        return null;
//    }
//
//    /**
//     * 用户根据id搜索商品的特殊运费规则
//     *
//     * @param id ：Integer
//     * @return SpecialFreight（不可获取下架商品）
//     */
//    @Override
//    public SpecialFreight getSpecialFreightsByForSaleGoodsId(Integer id) {
//        Goods goods = goodsDao.selectById(id);
//        if (goods != null) {
//            if (goods.getStatusCode() != 0) {
//                //调用运费服务
//            }
//        }
//        return null;
//    }

    /**
     * 管理员根据条件搜索商品
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
     * 用户根据条件搜素商品
     *
     * @param goodsName :String 商品的名字
     * @param page      :Integer     第几页
     * @param limit     :Integer    一页多少
     * @return List<Goods>,搜索到的商品的列表（不可获取下架商品）
     */
    @Override
    public List<Goods> listGoodsForSaleByCondition(String goodsName, Integer page, Integer limit) {
        return goodsDao.selectForSaleByCondition(null, goodsName, page, limit);
    }

    /**
     * 管理员根据商品分类id搜索该分类下的所有商品（看得到下架商品）
     *
     * @param id    :Integer
     * @param page  :Integer            第几页
     * @param limit :Integer           一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    @Override
    public List<Goods> listGoodsByCategoryId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectByCategoryId(id, page, limit);
    }

    /**
     * 用户根据商品分类id搜索该分类下的所有商品(看不到下架商品)
     *
     * @param id    :Integer
     * @param page  :Integer            第几页
     * @param limit :Integer           一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    @Override
    public List<Goods> listGoodsForSaleByCategoryId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectForSaleByCategoryId(id, page, limit);
    }

    /**
     * 管理员根据品牌id搜索该品牌下的所有商品（可以看到下架商品）
     *
     * @param id    :Integer
     * @param page  :                   Integer 第几页
     * @param limit :                  Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    @Override
    public List<Goods> listGoodsByBrandId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectByBrandId(id, page, limit);
    }

    /**
     * 用户根据品牌id搜索该品牌下的所有商品（无法看到下架商品）
     *
     * @param id    :Integer
     * @param page  :                   Integer 第几页
     * @param limit :                  Integer 一页多少
     * @return List<Goods>，搜索到的商品的列表
     */
    @Override
    public List<Goods> listGoodsForSaleByBrandId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectForSaleByBrandId(id, page, limit);
    }

    /**
     * 管理员新建商品
     *
     * @param goods ：Goods
     * @return Goods，新建的商品
     */
    @Override
    public Goods addGoods(Goods goods) {
        return goodsDao.insert(goods);
    }

    /**
     * 管理员根据id修改商品
     *
     * @param goods :Goods
     * @return Goods，修改后的商品
     */
    @Override
    public Goods updateGoodsById(Goods goods) {
        return goodsDao.updateById(goods);
    }

    /**
     * 管理员根据id删除商品
     *
     * @param id ：Integer
     * @return Integer,-1表示删除失败，0表示该商品仍在售，1表示删除成功
     */
    @Override
    public Integer deleteGoodsById(Integer id) {
        Goods goods = goodsDao.selectById(id);
        if (goods != null) {
            if (goods.getStatusCode() == 0) {
                if (goodsDao.deleteById(id)) {
                    return 1;
                }
            } else {
                return 0;
            }
        }
        return -1;
    }

    /**
     * 内部接口————————————判断商品是否在售
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Integer isGoodsOnSale(Integer id) {
        Goods goods = goodsDao.selectById(id);
        if (goods != null) {
            return goods.getStatusCode();
        }
        return -1;
    }

    /**
     * 管理员根据id搜索产品
     *
     * @param id :Integer
     * @return Product，搜索到的产品
     */
    @Override
    public Product getProductById(Integer id) {
        return productDao.selectById(id);
    }

    /**
     * 管理员搜索某个商品下的所有产品
     *
     * @param id    :Integer
     * @param page  :Integer            第几页
     * @param limit :Integer           一页多少
     * @return List<Product>，所属该商品的产品列表
     */
    @Override
    public List<Product> listProductsByGoodsId(Integer id, Integer page, Integer limit) {
        return productDao.selectByGoodsId(id, page, limit);
    }

    /**
     * 管理员新建某个商品下的产品
     *
     * @param product :Product
     * @return Product，新建的商品
     */
    @Override
    public Product addProduct(Product product) {
        return productDao.insert(product);
    }

    /**
     * 管理员根据id修改产品
     *
     * @param product :Product
     * @return Product，修改后的产品
     */
    @Override
    public Product updateProductById(Product product) {
        return productDao.updateById(product);
    }

    /**
     * 管理员根据id修改Product的库存
     *
     * @param id       ：Integer
     * @param quantity :Integer
     * @return Boolean ，修改成功与否
     */
    @Override
    public Boolean updateStockByProductId(Integer id, Integer quantity) {
        return productDao.descStock(id,quantity);
    }

    /**
     * 管理员根据id删除产品
     *
     * @param id :Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteProductById(Integer id) {
        return productDao.deleteById(id);
    }

    /**
     * 管理员或用户根据id搜索品牌
     *
     * @param id :Integer
     * @return Brand
     */
    @Override
    public Brand getBrandById(Integer id) {
        return brandDao.selectById(id);
    }

    /**
     * 管理员根据条件搜索品牌
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
     * 用户搜索所有品牌
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
     * 管理员创建品牌
     *
     * @param brand :Brand 要添加的品牌(body包含name、description、picURL(上传图片产生))
     * @return Brand
     */
    @Override
    public Brand addBrand(Brand brand) {
        return brandDao.insert(brand);
    }

    /**
     * 管理员修改品牌
     *
     * @param brand ：Brand(body包含name、description、picURL(上传图片产生))
     * @return Brand
     */
    @Override
    public Brand updateBrandById(Brand brand) {
        return brandDao.updateById(brand);
    }

    /**
     * 管理员根据id删除品牌
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteBrandById(Integer id) {
        return brandDao.deleteById(id);
    }

    /**
     * 管理员或用户根据id搜索分类
     *
     * @param id ：Integer
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory getGoodsCategoryById(Integer id) {
        return goodsCategoryDao.selectById(id);
    }

    /**
     * 管理员或用户搜索所有分类
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
     * 内部接口————————搜索所有一级分类
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
     * 管理员或用户搜索某一级分类下的所有二级分类
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

    /**
     * 管理员新建分类
     *
     * @param goodsCategory ：GoodsCategory(body包含name、pid(可以为空) 、picURL(上传图片产生))
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryDao.insert(goodsCategory);
    }

    /**
     * 管理员修改分类
     *
     * @param goodsCategory ：GoodsCategory(body包含name、pid(可以为空)、picURL(上传图片产生))
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory updateGoodsCategoryById(GoodsCategory goodsCategory) {
        return goodsCategoryDao.updateById(goodsCategory);
    }

    /**
     * 管理员删除分类
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteGoodsCategoryById(Integer id) {
        return goodsCategoryDao.deleteById(id);
    }

    /**
     * 上传图片
     *
     * @return picUrl：String，上传图片的url
     */
    @Override
    public String uploadPic() {
        return null;
    }
}
