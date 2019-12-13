package xmu.oomall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.dao.BrandDAO;
import xmu.oomall.dao.GoodsCategoryDAO;
import xmu.oomall.dao.GoodsDAO;
import xmu.oomall.dao.ProductDAO;
import xmu.oomall.domain.*;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.service.GoodsService;

import java.util.List;

/**
 * @Author Ke
 * @Description: GoodsServiceImpl
 * @create 2019/12/13 15:15
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
     * 管理员或用户根据id搜索商品
     *
     * @param id ：Integer
     * @return Goods，搜索到的商品，此URL与WX端是同一个URL
     */
    @Override
    public Goods getGoodsById(Integer id) {
        return goodsDao.selectById(id);
    }

    /**
     * 内部接口————————————判断商品是否在售
     *
     * @param id ：Integer
     * @return Integer，0表示该商品下架，1表示该商品在售，-1表示该商品不存在
     */
    @Override
    public Integer isGoodsOnSale(Integer id) {
        Goods goods = goodsDao.selectById(id);
        if (goods == null) {
            return -1;
        } else {
            return goods.getStatusCode();
        }
    }

    /**
     * 管理员根据条件搜索商品
     *
     * @param goodsSn   :String   商品的序列号
     * @param goodsName :String 商品的名字
     * @param status    :Integer   商品是否上架，这个域的取值以数据字典为准
     * @param page      :            Integer 第几页
     * @param limit     :           Integer 一页多少
     * @return List<GoodsPo>,搜索到的商品的列表
     */
    @Override
    public List<GoodsPo> listGoodsByCondition(String goodsSn, String goodsName, Integer status, Integer page, Integer limit) {
        return goodsDao.selectByCondition(goodsSn, goodsName, status, page, limit);
    }

    /**
     * 用户根据条件搜素商品
     *
     * @param goodsSn   :String   商品的序列号
     * @param goodsName :String 商品的名字
     * @param page      :Integer     第几页
     * @param limit     :Integer    一页多少
     * @return List<GoodsPo>,搜索到的商品的列表
     */
    @Override
    public List<GoodsPo> listGoodsByCondition(String goodsSn, String goodsName, Integer page, Integer limit) {
        return goodsDao.selectByCondition(goodsSn, goodsName, null,page, limit);
    }

    /**
     * 用户根据商品分类搜索商品
     *
     * @param id    :Integer
     * @param page  :Integer            第几页
     * @param limit :Integer           一页多少
     * @return List<GoodsPo>，搜索到的商品的列表
     */
    @Override
    public List<GoodsPo> ListGoodsByCategoryId(Integer id, Integer page, Integer limit) {
        return goodsDao.selectByCategoryId(id, page, limit);
    }

    /**
     * 管理员新建商品
     *
     * @param goodsPo ：GoodsPo
     * @return GoodsPo，新建的商品
     */
    @Override
    public GoodsPo addGoods(GoodsPo goodsPo) {
        return goodsDao.insert(goodsPo);
    }

    /**
     * 管理员根据id修改商品
     *
     * @param id      ：Integer
     * @param goodsPo :GoodsPo
     * @return GoodsPo，修改后的商品
     */
    @Override
    public GoodsPo updateGoodsById(Integer id, GoodsPo goodsPo) {
        if (goodsPo.getId().equals(id)) {
            if (goodsDao.updateById(goodsPo)) {
                return goodsPo;
            }
        }
        return null;
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
     * @return List<ProductPo>，所属该商品的产品列表
     */
    @Override
    public List<ProductPo> listProductsByGoodsId(Integer id, Integer page, Integer limit) {
        return productDao.selectByGoodsId(id, page, limit);
    }

    /**
     * 管理员新建某个商品下的产品
     *
     * @param id        :Integer
     * @param productPo :ProductPo
     * @return ProductPo，属于这个商品的产品列表
     */
    @Override
    public ProductPo addProduct(Integer id, ProductPo productPo) {
        if (productPo.getGoodsId().equals(id)) {
            if (productDao.insert(productPo)) {
                return productPo;
            }
        }
        return null;
    }

    /**
     * 管理员根据id修改产品
     *
     * @param id        :Integer
     * @param productPo :ProductPo
     * @return ProductPo，修改后的产品
     */
    @Override
    public ProductPo updateProductById(Integer id, ProductPo productPo) {
        if (productPo.getId().equals(id)) {
            if (productDao.updateById(productPo)) {
                return productPo;
            }
        }
        return null;
    }

    /**
     * 管理员根据id删除产品
     *
     * @param id :Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteProductById(Integer id) {
        if (productDao.deleteById(id)) {
            return true;
        } else {
            return false;
        }
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
        return brandDao.selectBrandsByCondition(page, limit);
    }

    /**
     * 管理员创建品牌
     *
     * @param brandPo:BrandPo
     * @return BrandPo
     */
    @Override
    public BrandPo addBrand(BrandPo brandPo) {
        return brandDao.insert(brandPo);
    }

    /**
     * 管理员修改品牌
     *
     * @param id      ：Integer
     * @param brandPo ：BrandPo
     * @return BrandPo
     */
    @Override
    public BrandPo updateBrandById(Integer id, BrandPo brandPo) {
        if (brandPo.getId().equals(id)) {
            if (brandDao.updateById(brandPo)) {
                return brandPo;
            }
        }
        return null;
    }

    /**
     * 管理员根据id删除品牌
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteBrandById(Integer id) {
        if (brandDao.deleteById(id)) {
            return true;
        } else {
            return false;
        }
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
     * 管理员或用户搜索所有一级分类
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
     * @return GoodsCategory
     */
    @Override
    public GoodsCategory listSecondLevelGoodsCategoryById(Integer id, Integer page, Integer limit) {
        return goodsCategoryDao.selectSecondLevelGoodsCategories(id, page, limit);
    }

    /**
     * 管理员新建分类
     *
     * @param goodsCategoryPo ：GoodsCategoryPo
     * @return GoodsCategoryPo
     */
    @Override
    public GoodsCategoryPo addGoodsCategory(GoodsCategoryPo goodsCategoryPo) {
        return goodsCategoryDao.insert(goodsCategoryPo);
    }

    /**
     * 管理员修改分类
     *
     * @param id              ：Integer
     * @param goodsCategoryPo ：GoodsCategoryPo
     * @return GoodsCategoryPo
     */
    @Override
    public GoodsCategoryPo updateGoodsCategoryById(Integer id, GoodsCategoryPo goodsCategoryPo) {
        if (goodsCategoryPo.getId().equals(id)) {
            if (goodsCategoryDao.updateById(goodsCategoryPo)) {
                return goodsCategoryPo;
            }
        }
        return null;
    }

    /**
     * 管理员删除分类
     *
     * @param id ：Integer
     * @return Boolean
     */
    @Override
    public Boolean deleteGoodsCategory(Integer id) {
        if (goodsCategoryDao.deleteById(id)) {
            return true;
        } else {
            return false;
        }
    }
}