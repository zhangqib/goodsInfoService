package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import xmu.oomall.Application;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.po.GoodsPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(classes = Application.class)
@Transactional
class GoodsMapperTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void deleteByPrimaryKey() {
        System.out.println(goodsMapper.deleteByPrimaryKey(1));
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
        goods.setGmtCreate(LocalDateTime.now());
        goods.setGmtModified(LocalDateTime.now());
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
        try {
            goodsMapper.insert(goods);
            System.out.println(goods);
        } catch(DuplicateKeyException e) {
            System.out.println("goodsSn error");
        }
    }

    @Test
    void selectByPrimaryKey() {
        System.out.println(goodsMapper.selectByPrimaryKey(1));
    }

    @Test
    void updateByPrimaryKey() {
//        GoodsPo goods = goodsMapper.selectByPrimaryKey(4);
        Goods goods = new Goods();
        goods.setId(7);
        goods.setShortName("ee");
        System.out.println(goodsMapper.updateByPrimaryKey(goods));
    }

    @Test
    void selectByCategoryId() {
        goodsMapper.selectByCategoryId(1).forEach(goodsPo -> System.out.println(goodsPo));
    }

    @Test
    void selectForSaleByCategoryId() {
        goodsMapper.selectForSaleByCategoryId(3).forEach(goodsPo -> System.out.println(goodsPo));
    }

    @Test
    void selectByBrandId() {
        goodsMapper.selectByBrandId(3).forEach(goodsPo -> System.out.println(goodsPo));
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
        System.out.println(goodsMapper.cleanBrand(1));
    }

    @Test
    void cleanCategory() {
        System.out.println(goodsMapper.cleanCategory(1));
    }
}