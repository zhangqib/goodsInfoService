package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.BrandMapper;
import xmu.oomall.mapper.GoodsCategoryMapper;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;
import xmu.oomall.util.Copyer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang
 */
@Repository
public class GoodsDAO {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 将商品插入数据库
     * 商品的品牌和类别的必须存在（如果有传品牌或类别id的话）
     *
     * @param goods 要添加的商品
     * @return 成功：更新完id的商品 失败： null
     */
    public Goods insert(Goods goods) {
        /* 判断参数是否合法 */
        // beDeleted不能为true
        if (goods.getBeDeleted()) {
            return null;
        }
        // 维护外键
        //   brandId
        Integer brandId = goods.getBrandId();
        if (brandId != null && brandMapper.selectByPrimaryKey(brandId) == null) {
            return null;
        }
        //   categoryId
        Integer goodsCategoryId = goods.getGoodsCategoryId();
        if (goodsCategoryId != null && goodsCategoryMapper.selectByPrimaryKey(goodsCategoryId) == null) {
           return null;
        }
        //   spcialFreightId
        //    未完成(需要调用运费服务)
        //    .......

        // 判断goodsSn是否重复
        String goodsSn = goods.getGoodsSn();
        if (goodsSn != null && goodsMapper.selectByCondition(goodsSn, null, null) != null) {
           return null;
        }
        /* 参数合法性判断完成 */

        /* 判断插入是否成功 */
        if (goodsMapper.insert(goods) > 0) {
            return goods;
        } else {
            return null;
        }
    }

    /**
     * 根据id删除Goods，级联删除其对应的product
     *
     * @param id 要删除的商品id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        List<ProductPo> products = productMapper.selectByGoodsId(id);
        for (ProductPo product : products) {
            if (productMapper.deleteByPrimaryKey(product.getId()) == 0) {
                return false;
            }
        }
        return goodsMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 根据id返回对应的商品
     *
     * @param id 要查寻商品的id
     * @return Goods
     */
    public Goods selectById(Integer id) {
        GoodsPo goods = goodsMapper.selectByPrimaryKey(id);
        return goods(goods);
    }

    /**
     * 查询所有商品
     */
    public List<Goods> selectAll(Integer page, Integer limit) {
        return this.selectByCondition(null, null, null, page, limit);
    }

    /**
     * 查询所有未下架的商品
     */
    public List<Goods> selectAllForSale(Integer page, Integer limit) {
       return this.selectForSaleByCondition(null, null, page, limit);
    }

    /**
     * 根据productId返回Goods
     *
     * @param productId
     * @return productId所属的商品
     */
    public Goods selectByProductId(Integer productId) {
        ProductPo product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return null;
        }
        GoodsPo goods = goodsMapper.selectByPrimaryKey(product.getGoodsId());
        return goods(goods);
    }

    /**
     * 根据参数的id更新对应的goods
     *
     * @param goods 新的商品
     * @return 更新是否成功
     */
    public Goods updateById(Goods goods) {
        /* 判断传入的参数是否合法 */
        if (isArgsInvalid(goods)) {
            return null;
        }
        if (goodsMapper.updateByPrimaryKey(goods) == 0) {
            return null;
        }

        // 判断是否有可更新的属性(若是空对象，只有id会出错）
        // 感觉应该放在controller

        return goods(goodsMapper.selectByPrimaryKey(goods.getId()));
    }

    /**
     * 根据类别返回商品
     *
     * @param id    类别的id
     * @param page  页码
     * @param limit 页的大小
     * @return 该类别的商品列表
     */
    public List<Goods> selectByCategoryId(Integer id, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<GoodsPo> goodsPos = goodsMapper.selectByCategoryId(id);
        return goodsList(goodsPos);
    }

    public List<Goods> selectByCondition(String goodsSn, String name, Integer statusCode, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsList(goodsMapper.selectByCondition(goodsSn, name, statusCode));
    }

    /**
     * 将GoodsPo转换成Goods对象
     */
    private Goods goods(GoodsPo goodsPo) {
        Goods goods = new Goods();
        return Copyer.Copy(goodsPo, goods) ? goods : null;
    }

    /**
     * 将goodsPo列表转换成goods类表
     *
     * @return goodsList
     */
    private List<Goods> goodsList(List<GoodsPo> goodsPoList) {
        if (goodsPoList == null) {
            return null;
        }
        List<Goods> goodsList = new ArrayList<>();
        for (GoodsPo goodsPo : goodsPoList) {
            goodsList.add(goods(goodsPo));
        }
        return goodsList;
    }

    /**
     * 判断goods的品牌和类别是否合法
     */
    private boolean isArgsInvalid(Goods goods) {
        // beDeleted不能为true
        if (goods.getBeDeleted()) {
            return true;
        }
        // 维护外键
        //   brandId
        Integer brandId = goods.getBrandId();
        if (brandId != null && brandMapper.selectByPrimaryKey(brandId) == null) {
            return true;
        }
        //   categoryId
        Integer goodsCategoryId = goods.getGoodsCategoryId();
        return goodsCategoryId != null && goodsCategoryMapper.selectByPrimaryKey(goodsCategoryId) == null;
        //   spcialFreightId
        //    未完成(需要调用运费服务)
    }

    /**
     * 通过brand查找商品
     */
    public List<Goods> selectByBrandId(Integer id, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsList(goodsMapper.selectByBrandId(id));
    }

    /**
     * 条件查询未下架商品
     */
    public List<Goods> selectForSaleByCondition(String goodsSn, String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsList(goodsMapper.selectForSaleByCondition(goodsSn, name));
    }

    /**
     * 查询类别下的未下架商品
     */
    public List<Goods> selectForSaleByCategoryId(Integer categoryId, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsList(goodsMapper.selectForSaleByCategoryId(categoryId));
    }

    /**
     * 通过brand查询未下架商品
     */
    public List<Goods> selectForSaleByBrandId(Integer brandId, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return goodsList(goodsMapper.selectForSaleByBrandId(brandId));
    }
}
