package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.domain.po.GoodsPo;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Transactional
class GoodsMapperTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void deleteByPrimaryKey() {
        Integer goodsId = 273;
        Assert.isTrue(goodsMapper.deleteByPrimaryKey(273) == 1, "delete goods failed");
        System.out.println(goodsMapper.selectByPrimaryKey(goodsId));
        Assert.isTrue(goodsMapper.selectByPrimaryKey(goodsId).getBeDeleted(), "delete goods error");
    }

    @Test
    void insert() {
        GoodsPo goods = new GoodsPo();
        goods.setBeDeleted(false);
        goods.setBeSpecial(false);
        goods.setBrandId(1);
        goods.setBrief("test goods");
        goods.setDescription("test description");
        goods.setDetail("test detail");
        goods.setGallery("test Gallery");
        goods.setGoodsCategoryId(1);
        goods.setName("test goods");
        goods.setPicUrl("test url");
        goods.setShareUrl("test url");
        goods.setShortName("t");
        goods.setSpecialFreightId(1);
        goods.setStatusCode(0);
        goods.setVolume("1");
        goods.setWeight(new BigDecimal(1));
        goods.setGoodsSn("1");
        Assert.isTrue(goodsMapper.insert(goods) > 0, "insert goods failed");
    }

    @Test
    void selectByPrimaryKey() {
        Integer goodsId = 273;
        Assert.notNull(goodsMapper.selectByPrimaryKey(goodsId), "select goods failed");
    }

    @Test
    void updateByPrimaryKey() {
        GoodsPo goods = goodsMapper.selectByPrimaryKey(273);
        goods.setName("ee");
        Assert.isTrue(goodsMapper.updateByPrimaryKey(goods) == 1, "update failed");
        Assert.isTrue(goodsMapper.selectByPrimaryKey(273).getName().equals("ee"), "update error");
    }

    @Test
    void selectByCategoryId() {
        Integer categoryId = 128;
        List<GoodsPo> goodsList = goodsMapper.selectByCategoryId(categoryId);
        Assert.notEmpty(goodsList, "select by category failed");
        goodsList.forEach(goodsPo -> Assert.isTrue(goodsPo.getGoodsCategoryId().equals(categoryId), "select by category error"));
    }

    @Test
    void selectForSaleByCategoryId() {
        goodsMapper.selectForSaleByCategoryId(3).forEach(goodsPo -> System.out.println(goodsPo));
    }

    @Test
    void selectByBrandId() {
        Integer brandId = 103;
        List<GoodsPo> goodsList = goodsMapper.selectByBrandId(brandId);
        Assert.notEmpty(goodsList, "select by brand failed");
        goodsList.forEach(goodsPo -> Assert.isTrue(goodsPo.getBrandId().equals(brandId), "select by brand error"));
    }

    @Test
    void selectForSaleByBrandId() {
        goodsMapper.selectForSaleByBrandId(3).forEach(
                goodsPo -> System.out.println(goodsPo)
        );
    }

    @Test
    void selectByCondition() {
       goodsMapper.selectByCondition( null, "test goods", 0).forEach(
               goodsPo -> System.out.println(goodsPo)
       );
    }

    @Test
    void selectForSaleByCondition() {
        goodsMapper.selectForSaleByCondition("3", null).forEach(
                goodsPo -> System.out.println(goodsPo));
    }

    @Test
    void cleanBrand() {
        System.out.println(goodsMapper.cleanBrand(103));
    }

    @Test
    void cleanCategory() {
        System.out.println(goodsMapper.cleanCategory(128));
    }
}