package xmu.oomall.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.controller.vo.GoodsVo;
import xmu.oomall.domain.*;

import java.util.List;


/**
 * @Author Ke
 * @Description: GoodsServiceInterface
 * @create 2019/12/12 22:22
 */


@Service
public interface GoodsService {

    /**
     * 管理员查询商品下的产品
     *
     * @param id
     * @return List<Product>，所属该商品的产品列表
     */
    public List<Product> listProductByGoodsId(Integer id);

    /**
     * 管理员添加商品下的产品
     *
     * @param id
     * @param product
     * @return Product，新添加的产品信息
     */
    public Product addProductByGoodsId( Integer id,  Product product);

    /**
     * 管理员修改商品下的某个产品信息
     *
     * @param id
     * @param product
     * @return Product，修改后的产品信息
     */
    public Product updateProductById( Integer id,  Product product);

    /**
     * 管理员删除商品下的某个产品信息
     *
     * @param id
     * @return boolean
     */
    public boolean deleteProductById( Integer id);

//    /**
//     * 获取商品列表
//     * @return
//     */
//    @GetMapping("/goods")
//    public Object listGoods();


    /**
     * 根据id获取某个商品
     *
     * @param id
     * @return GoodsVo，即商品的信息，此URL与WX端是同一个URL
     */
    public GoodsVo getGoodsById(Integer id);

    /**
     * 新建/上架一个商品
     *
     * @param goods
     * @return Goods，即新建的一个商品
     */
    public Goods addGoods( Goods goods);

    /**
     * 根据id更新商品信息
     *
     * @param id
     * @param goods
     * @return Goods，修改后的商品信息
     */
    public Goods updateGoodsById( Integer id,  Goods goods);

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return boolean
     */
    public boolean deleteGoodsById( Integer id);

    /**
     * 获取商品分类信息
     *
     * @param id
     * @return
     */
    public List<Goods> getCategoriesInfoById( Integer id);

    /**
     * 根据条件搜索商品
     *
     * @param goodsSn 商品的序列号
     * @param name    商品的名字
     * @param page    第几页
     * @param limit   一页多少
     *                //     * @param sort
     *                //     * @param order
     * @return List<Goods>
     */
    public List<Goods> listGoods( String goodsSn,
                             String name,
                             Integer page,
                             Integer limit
//                             String sort,
//                             String order
    );

//    /**
//     * 根据条件搜索品牌
//     *
//     * @return List<Brand>
//     */
//    @GetMapping("/admins/brands")
//    public Object listBrand() {
//        return null;
//    }


    /**
     * 创建一个品牌
     * @param brand
     * @return Brand
     */
    public Brand addBrand( Brand brand);

    /**
     * 查看品牌详情,此API与商城端/brands/{id}完全相同
     * @param id
     * @return Brand
     */
    public Brand getBrandById( Integer id);

    /**
     * 修改单个品牌的信息
     * @param id
     * @param brand
     * @return Brand
     */
    public Brand updateBrandById( Integer id,  Brand brand);

    /**
     * 删除一个品牌
     * @param id
     * @return boolean
     */
    public boolean deleteBrandById( Integer id);

    /**
     * 查看所有的分类
     * @return List<GoodsCategory>
     */
    public List<GoodsCategory> listGoodsCategory();

    /**
     * 新建一个分类
     * @param goodsCategory
     * @return GoodsCategory
     */
    public GoodsCategory addGoodsCategory( GoodsCategory goodsCategory);

    /**
     * 查看单个分类信息
     * @param id
     * @return GoodsCategory
     */
    public GoodsCategory getGoodsCategoryById( Integer id);

    /**
     * 修改分类信息
     * @param id
     * @param goodsCategory
     * @return GoodsCategory
     */
    public GoodsCategory updateGoodsCategoryById( Integer id,  GoodsCategory goodsCategory);

    /**
     * 删除单个分类
     * @param id
     * @param goodsCategory
     * @return boolean
     */
    public boolean deleteGoodsCategory( Integer id,  GoodsCategory goodsCategory);

    /**
     * 查看所有一级分类
     * @return List<GoodsCategory>
     */
    public List<GoodsCategory> listOneLevelGoodsCategory();

    /**
     * 查看所有品牌
     * @return List<Brand>
     */
    public List<Brand> listBrand();

    /**
     * 获取当前一级分类下的二级分类
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    public List<GoodsCategory> listSecondLevelGoodsCategoryById( Integer id);

    /**
     * 根据id获得产品对象 - 内部
     * @param id
     * @return Product
     */
    public Product getProductById( Integer id);

    /**
     * 判断商品是否在售 - 内部
     * @param id
     * @return boolean
     */
    public boolean isGoodsOnSale( Integer id);

}
