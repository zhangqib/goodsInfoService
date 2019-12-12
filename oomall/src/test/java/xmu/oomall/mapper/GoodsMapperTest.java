package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.Application;
import xmu.oomall.domain.po.GoodsPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(classes = Application.class)
class GoodsMapperTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void deleteByPrimaryKey() {
        System.out.println(goodsMapper.deleteByPrimaryKey(4));
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
        goods.setGoodsSn("1");
        goods.setName("test goods");
        goods.setPicUrl("test url");
        goods.setShareUrl("test url");
        goods.setShortName("t");
        goods.setSpecialFreightId(1);
        goods.setStatusCode(0);
        goods.setVolume("1");
        goods.setWeight(new BigDecimal(1));
        goodsMapper.insert(goods);
        System.out.println(goods);
    }

    @Test
    void selectByPrimaryKey() {
        System.out.println(goodsMapper.selectByPrimaryKey(4));
    }

    @Test
    void updateByPrimaryKey() {
        GoodsPo goods = goodsMapper.selectByPrimaryKey(4);
        goods.setGoodsSn("12");
        System.out.println(goodsMapper.updateByPrimaryKey(goods));
    }

    @Test
    void selectByCategoryId() {
        System.out.println(goodsMapper.selectByCategoryId(1));
    }
}