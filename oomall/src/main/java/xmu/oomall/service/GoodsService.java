package xmu.oomall.service;

import org.springframework.stereotype.Service;
import xmu.oomall.controller.vo.*;
import xmu.oomall.domain.*;
import xmu.oomall.util.ResponseUtil;
import java.util.List;

/**
 * @Author Ke
 */
@Service
public interface GoodsService {

    /**
     * 根据id获得产品对象
     *
     * @param id
     * @return
     */
    public Product getProductById(Integer id);

    /**
     * 管理员查询商品下的产品
     *
     * @param id
     * @return
     */
    public List<Product> listProductByGoodsId(Integer id);

    /**
     * 管理员添加商品下的产品
     *
     * @param id
     * @return
     */
    public Product addProductByGoodsId(Integer id,ProductVo productVo);

    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id
     * @return
     */
    public Product updateProductById(Integer id,ProductVo productVo);

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id
     * @return
     */
    public ResponseUtil deleteProductById(Integer id);
    /**
     * 新建商品
     *
     * @param goodsVo
     * @return
     */
    public Goods addGoods(GoodsVo goodsVo);

    /**
     * 根据id获取某个商品
     *
     * @param id
     * @return
     */
    public Goods getGoodsById(Integer id);

    /**
     * 根据id更新商品信息
     *
     * @param goodsVo
     * @return
     */
    public Goods updateGoodsById(Integer id, GoodsVo goodsVo);

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return
     */
    public ResponseUtil deleteGoodsById(Integer id);

    /**
     * 获取商品分类信息
     *
     * @return
     */
    public GoodsCategory getCategoriesInfoById(Integer id);

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
    public List<Goods> listGoods(String goodsSn, String name, Integer page,Integer limit,String sort,String order);

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
    public List<Brand> listBrandByCondition(String id, String name,Integer page, Integer limit,String sort,String order);


    /**
     * 创建一个品牌
     *
     * @param brand
     * @return
     */
    public Brand addBrand(Brand brand);

    /**
     * 查看品牌详情,此API与商城端/brands/{id}完全相同
     *
     * @param id
     * @return
     */
    public Brand getBrandById(Integer id);

    /**
     * 修改单个品牌的信息
     *
     * @param id
     * @param brand
     * @return
     */
    public Brand updateBrandById(Integer id, Brand brand);

    /**
     * 删除一个品牌
     *
     * @param id
     * @return
     */
    public ResponseUtil deleteBrandById(Integer id);
    /**
     * 查看所有的分类
     *
     * @return
     */
    public List<GoodsCategory> listGoodsCategory();

    /**
     * 新建一个分类
     *
     * @param goodsCategory
     * @return
     */
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 查看单个分类信息
     *
     * @param id
     * @return
     */
    public GoodsCategory getGoodsCategoryById(Integer id);

    /**
     * 修改分类信息
     *
     * @param id
     * @param goodsCategory
     * @return
     */
    public GoodsCategory updateGoodsCategoryById(Integer id,GoodsCategory goodsCategory);

    /**
     * 删除单个分类
     *
     * @param id
     * @return
     */
    public ResponseUtil deleteGoodsCategory(Integer id);

    /**
     * 查看所有一级分类
     *
     * @return
     */
    public List<GoodsCategory> listOneLevelGoodsCategory();

    /**
     * 获取当前一级分类下的二级分类
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    public List<GoodsCategory> listSecondLevelGoodsCategoryById(Integer id);

    /**
     * 查看所有品牌
     *
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<Brand> listBrand(Integer page,Integer limit,String sort,String order);



}
