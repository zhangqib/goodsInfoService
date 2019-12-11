package xmu.oomall.service.impl;

import org.springframework.stereotype.Service;
import xmu.oomall.controller.VO.GoodsVo;
import xmu.oomall.controller.VO.ProductVo;
import xmu.oomall.domain.Brand;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.GoodsCategory;
import xmu.oomall.domain.Product;
import xmu.oomall.service.GoodsService;
import xmu.oomall.util.ResponseUtil;
import java.util.List;

/**
 * @Author Ke
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    /**
     * 根据id获得产品对象
     *
     * @param id
     * @return
     */
    @Override
    public Product getProductById(Integer id) {
        return null;
    }

    /**
     * 管理员查询商品下的产品
     *
     * @param id
     * @return
     */
    @Override
    public List<Product> listProductByGoodsId(Integer id) {
        return null;
    }

    /**
     * 管理员添加商品下的产品
     *
     * @param id
     * @return
     */
    @Override
    public Product addProductByGoodsId(Integer id, ProductVo productVo) {
        return null;
    }

    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id
     * @return
     */
    @Override
    public Product updateProductById(Integer id, ProductVo productVo) {
        return null;
    }

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseUtil deleteProductById(Integer id) {
        return null;
    }

    /**
     * 新建商品
     *
     * @param goodsVo
     * @return
     */
    @Override
    public Goods addGoods(GoodsVo goodsVo) {
        return null;
    }

    /**
     * 根据id获取某个商品
     *
     * @param id
     * @return
     */
    @Override
    public Goods getGoodsById(Integer id) {
        return null;
    }

    /**
     * 根据id更新商品信息
     *
     * @param goodsVo
     * @return
     */
    @Override
    public Goods updateGoodsById(Integer id, GoodsVo goodsVo) {
        return null;
    }

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseUtil deleteGoodsById(Integer id) {
        return null;
    }

    /**
     * 获取商品分类信息
     *
     * @return
     */
    @Override
    public GoodsCategory getCategoriesInfoById(Integer id) {
        return null;
    }

    /**
     * 根据条件搜素商品
     *
     * @param goodsSn
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Goods> listGoods(String goodsSn, String name, Integer page, Integer limit, String sort, String order) {
        return null;
    }

    /**
     * 根据条件搜索品牌
     *
     * @param id
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Brand> listBrandByCondition(String id, String name, Integer page, Integer limit, String sort, String order) {
        return null;
    }


    /**
     * 创建一个品牌
     *
     * @param brand
     * @return
     */
    @Override
    public Brand addBrand(Brand brand) {
        return null;
    }

    /**
     * 查看品牌详情,此API与商城端/brands/{id}完全相同
     *
     * @param id
     * @return
     */
    @Override
    public Brand getBrandById(Integer id) {
        return null;
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
        return null;
    }

    /**
     * 删除一个品牌
     *
     * @param id
     * @return
     */
    @Override
    public ResponseUtil deleteBrandById(Integer id) {
        return null;
    }

    /**
     * 查看所有的分类
     *
     * @return
     */
    @Override
    public List<GoodsCategory> listGoodsCategory() {
        return null;
    }

    /**
     * 新建一个分类
     *
     * @param goodsCategory
     * @return
     */
    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return null;
    }

    /**
     * 查看单个分类信息
     *
     * @param id
     * @return
     */
    @Override
    public GoodsCategory getGoodsCategoryById(Integer id) {
        return null;
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
        return null;
    }

    /**
     * 删除单个分类
     *
     * @param id
     * @return
     */
    @Override
    public ResponseUtil deleteGoodsCategory(Integer id) {
        return null;
    }

    /**
     * 查看所有一级分类
     *
     * @return
     */
    @Override
    public List<GoodsCategory> listOneLevelGoodsCategory() {
        return null;
    }

    /**
     * 获取当前一级分类下的二级分类
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @Override
    public List<GoodsCategory> listSecondLevelGoodsCategoryById(Integer id) {
        return null;
    }

    /**
     * 查看所有品牌
     *
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @Override
    public List<Brand> listBrand(Integer page, Integer limit, String sort, String order) {
        return null;
    }


}
