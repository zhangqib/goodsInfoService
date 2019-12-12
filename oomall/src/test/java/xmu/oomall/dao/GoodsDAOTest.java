package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.Application;
import xmu.oomall.domain.Goods;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(classes = Application.class)
class GoodsDAOTest {

    @Autowired
    private GoodsDAO goodsDAO;

    @Test
    void insert() {
        Goods goods = new Goods();
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
        goods.setStatusCode(false);
        goods.setVolume("1");
        goods.setWeight(new BigDecimal(1));
        goodsDAO.insert(goods);
        System.out.println(goods);
    }

    @Test
    void deleteByPrimaryKey() {
        System.out.println();
    }

    @Test
    void selectById() {
        System.out.println(goodsDAO.selectById(3));
    }

    @Test
    void selectByProductId() {
    }

    @Test
    void updateById() {
    }
}