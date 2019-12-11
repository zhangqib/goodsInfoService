package xmu.oomall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.controller.vo.GoodsVo;
import xmu.oomall.controller.vo.ProductVo;
import xmu.oomall.dao.GoodsDAO;
import xmu.oomall.dao.ProductDAO;
import xmu.oomall.domain.Brand;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.GoodsCategory;
import xmu.oomall.domain.Product;
import xmu.oomall.service.GoodsService;
import xmu.oomall.util.ResponseUtil;
import java.lang.Object;
import java.util.List;

/**
 * @Author Ke
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
     * 根据id获得产品对象
     *
     * @param id
     * @return
     */
    @Override
    public Product getProductById(Integer id) {
        return productDao.selectById(id);
    }

    /**
     * 管理员查询商品下的产品
     *
     * @param id
     * @return
     */
    @Override
    public List<Product> listProductByGoodsId(Integer id) {
        return productDao.selectByGoodsId(id);
    }

    /**
     * 管理员添加商品下的产品
     *
     * @param id
     * @return
     */
    @Override
    public Product addProductByGoodsId(Integer id, ProductVo productVo){
        Product product =productVo.getProduct();
        return productDao.insert(product);
    }

    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id
     * @return
     */
    @Override
    public Product updateProductById(Integer id, ProductVo productVo) {
        Product product=productVo.getProduct();
        if (product.getId().equals(id)) {
            return productDao.updateById(product);
        }
        else{
            return null;
        }
    }

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id
     * @return
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
     * 新建商品
     *
     * @param goodsVo
     * @return
     */
    @Override
    public Goods addGoods(GoodsVo goodsVo) {
        Goods goods=goodsVo.getGoods();
        return goodsDao.insert(goods);
    }

    /**
     * 根据id获取某个商品
     *
     * @param id
     * @return
     */
    @Override
    public Goods getGoodsById(Integer id) {
        return goodsDao.selectById(id);
    }

    /**
     * 根据id更新商品信息
     *
     * @param goodsVo
     * @return
     */
    @Override
    public Goods updateGoodsById(Integer id, GoodsVo goodsVo) {
        Goods goods=goodsVo.getGoods();
        if(goods.getId().equals(id)) {
            return goodsDao.updateById(goods);
        }
        else{
            return null;
        }
    }

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return
     */
    @Override
    public Object deleteGoodsById(Integer id) {
        if (goodsDao.deleteByPrimaryKey(id)) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 获取某一类别下的商品
     *
     * @return
     */
    @Override
    public List<Goods> listGoodsByCategoryId(Integer id) {
        goodsDao.selectByCategoryId(id);
    }

    /**
     * 根据条件搜素商品
     *
     * @param goodsSn
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Goods> listGoodsByCondition(String goodsSn, String name, Integer page, Integer limit) {
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
     * 查看所有品牌
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Brand> listBrand(Integer page, Integer limit) {
        return brandDao.selectAllBrand(page,limit);

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
            return brandDao.updateById(brand);
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
        if (brandDao.deleteByPrimaryKey(id)) {
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail();
        }
    }
    /**
     * 根据条件搜索品牌
     *
     * @param id
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Brand> listBrandByCondition(String id, String name, Integer page, Integer limit) {
        return brandDao.selectBrandByCondition(id,name,page,limit);
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
        if(goodsCategoryDao.getId().equals(id)) {
            return goodsCategoryDao.updateById(goodsCategory);
        }
        else{
            return null;
        }
    }

    /**
     * 删除单个分类
     *
     * @param id
     * @return
     */
    @Override
    public Object deleteGoodsCategory(Integer id) {
        if (goodsCategoryDao.deleteByPrimaryKey(id)) {
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
     * 获取当前一级分类下的二级分类
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @Override
    public List<GoodsCategory> listSecondLevelGoodsCategoryById(Integer id) {
        return goodsCategoryDao.selectSecondLevelGoodsCategory();
    }




}
