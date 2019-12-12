package xmu.oomall.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.controller.vo.GoodsVo;
import xmu.oomall.domain.*;
import xmu.oomall.dao.*;
import xmu.oomall.service.GoodsService;
import xmu.oomall.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 模块标准组
 * @Description:商品模块外部及内部api
 * @create 2019/12/3 18:30
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
     * 管理员查询商品下的产品
     *
     * @param id
     * @return List<Product>，所属该商品的产品列表
     */
    @Override
    public List<Product> listProductByGoodsId(Integer id) {
        return productDao.selectByGoodsId(id);
    }

    /**
     * 管理员添加商品下的产品
     *
     * @param id
     * @param product
     * @return Product，新添加的产品信息
     */
    @Override
    public Product addProductByGoodsId(Integer id, Product product) {
        if (product.getGoodsId().equals(id)) {
            return productDao.insert(product);
        }
        else {
            return null;
        }
    }

    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id
     * @param product
     * @return Product，修改后的产品信息
     */
    @Override
    public Product updateProductById(Integer id, Product product) {
        if (product.getId().equals(id)) {
            if(productDao.updateById(product)){
                return product;
            }
            else{
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id
     * @return 无（ResponseUtil.ok()即可）
     */
    @Override
    public Object deleteProductById(Integer id) {
        if (productDao.deleteById(id)) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 根据id获取某个商品
     *
     * @param id
     * @return GoodsVo，即商品的信息，此URL与WX端是同一个URL
     */
    @Override
    public GoodsVo getGoodsById(Integer id) {
        Goods goods=goodsDao.selectById(id);
        GoodsVo goodsVo=new GoodsVo();
        Brand brand=brandDao.selectById(goods.getBrandId());
        goodsVo.setBrand(brand);
        goodsVo.setGoods(goods);
        GoodsCategory goodsCategory=goodsCategoryDao.selectById(goods.getGoodsCategoryId());
        goodsVo.setGoodsCategory(goodsCategory);
        GrouponRule grouponRule=new GrouponRule();
        goodsVo.setGrouponRule(grouponRule);
        List<Product> listProducts=new ArrayList<Product>();
        goodsVo.setProducts(listProducts);
        ShareRule shareRule=new ShareRule();
        goodsVo.setShareRules(shareRule);
        SpecialFreight specialFreight=new SpecialFreight();
        goodsVo.setSpecialFreight(specialFreight);
        return goodsVo;
    }

    /**
     * 新建/上架一个商品
     *
     * @param goods
     * @return Goods，即新建的一个商品
     */
    @Override
    public Goods addGoods(Goods goods) {
        return goodsDao.insert(goods);
    }

    /**
     * 根据id更新商品信息
     *
     * @param id
     * @param goods
     * @return Goods，修改后的商品信息
     */
    @Override
    public Goods updateGoodsById(Integer id, Goods goods) {
        if(goods.getId().equals(id)) {
            if(goodsDao.updateById(goods)){
                return goods;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return 无（即ResponseUtil.ok()即可）
     */
    @Override
    public Object deleteGoodsById(Integer id) {
        if (goodsDao.deleteById(id)) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 获取商品分类信息
     *
     * @param id
     * @return
     */
    @Override
    public List<Goods> getCategoriesInfoById(Integer id) {
        return goodsDao.selectByCategoryId(id);
    }

    /**
     * 根据条件搜索商品
     *
     * @param goodsSn 商品的序列号
     * @param name    商品的名字
     * @param page    第几页
     * @param limit   一页多少
     *                //     * @param sort
     *                //     * @param order
     * @return
     */
    @Override
    public List<Goods> listGoods(String goodsSn, String name, Integer page, Integer limit) {
        return goodsDao.selectGoodsByCondition(goodsSn,name,page,limit);
    }

    /**
     * 创建一个品牌
     *
     * @param brand
     * @return
     */
    @Override
    public Brand addBrand(Brand brand) {
        return brandDao.insert(brand);
    }

    /**
     * 查看品牌详情,此API与商城端/brands/{id}完全相同
     *
     * @param id
     * @return
     */
    @Override
    public Brand getBrandById(Integer id) {
        return brandDao.selectById(id);
    }

    /**
     * 修改单个品牌的信息
     *
     * @param id
     * @param brand
     * @return
     */
    @Override
    public Brand updateBrandById(Integer id, Brand brand) {
        if(brand.getId().equals(id)) {
            if(brandDao.updateById(brand)){
                return brand;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    /**
     * 删除一个品牌
     *
     * @param id
     * @return
     */
    @Override
    public Object deleteBrandById(Integer id) {
        if (brandDao.deleteById(id)) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 查看所有的分类
     *
     * @return
     */
    @Override
    public List<GoodsCategory> listGoodsCategory() {
        return goodsCategoryDao.selectAllGoodsCategory();
    }

    /**
     * 新建一个分类
     *
     * @param goodsCategory
     * @return
     */
    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryDao.insert(goodsCategory);
    }

    /**
     * 查看单个分类信息
     *
     * @param id
     * @return
     */
    @Override
    public GoodsCategory getGoodsCategoryById(Integer id) {
        return goodsCategoryDao.selectById(id);
    }

    /**
     * 修改分类信息
     *
     * @param id
     * @param goodsCategory
     * @return
     */
    @Override
    public GoodsCategory updateGoodsCategoryById(Integer id, GoodsCategory goodsCategory) {
        if(goodsCategory.getId().equals(id)) {
            if (goodsCategoryDao.updateById(goodsCategory)){
                return goodsCategory;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    /**
     * 删除单个分类
     *
     * @param id
     * @param goodsCategory
     * @return
     */
    @Override
    public Object deleteGoodsCategory(Integer id, GoodsCategory goodsCategory) {
        if (goodsCategoryDao.deleteById(id)) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 查看所有一级分类
     *
     * @return
     */
    @Override
    public List<GoodsCategory> listOneLevelGoodsCategory() {
        return goodsCategoryDao.selectOneLevelGoodsCategory();
    }

    /**
     * 查看所有品牌
     *
     * @return List<Brand>
     */
    @Override
    public List<Brand> listBrand() {
        return brandDao.selectAllBrand();
    }

    /**
     * 获取当前一级分类下的二级分类
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @Override
    public List<GoodsCategory> listSecondLevelGoodsCategoryById(Integer id) {
        return goodsCategoryDao.selectSecondLevelGoodsCategory(id);
    }

    /**
     * 根据id获得产品对象 - 内部
     *
     * @param id
     * @return
     */
    @Override
    public Product getProductById(Integer id) {
        return productDao.selectById(id);
    }

    /**
     * 判断商品是否在售 - 内部
     *
     * @param id
     * @return
     */
    @Override
    public boolean isGoodsOnSale(Integer id) {
        return goodsDao.isGoodsOnSale(id);
    }
}