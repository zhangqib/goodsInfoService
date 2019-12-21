package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.domain.po.GoodsPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class GoodsDAOTest {
    @Autowired
    private GoodsDAO goodsDAO;

    @Test
    void insert() {
        GoodsPo goods = new GoodsPo();
        goods.setBeDeleted(false);
        goods.setBeSpecial(false);
        goods.setBrandId(71);
        goods.setBrief("test goods");
        goods.setDescription("test description");
        goods.setDetail("test detail");
        goods.setGallery("test Gallery");
        goods.setGmtCreate(LocalDateTime.now());
        goods.setGmtModified(LocalDateTime.now());
        goods.setGoodsCategoryId(122);
        goods.setGoodsSn("1");
        goods.setName("test goods");
        goods.setPicUrl("test url");
        goods.setShareUrl("test url");
        goods.setShortName("t");
        goods.setSpecialFreightId(1);
        goods.setStatusCode(0);
        goods.setVolume("1");
        goods.setWeight(new BigDecimal(1));
        goodsDAO.insert(goods);
        System.out.println(goods);
        Assert.notNull(goods.getId(), "insert fail");
    }

    @Test
    void deleteById() {
        System.out.println(goodsDAO.deleteById(5));
    }

    @Test
    void selectById() {
        System.out.println(goodsDAO.selectById(5));
    }

    @Test
    void selectByProductId() {
        System.out.println(goodsDAO.selectByProductId(4));
    }

    @Test
    void selectByCategoryId() {
        System.out.println(goodsDAO.selectByCategoryId(3, 1, 3));
    }

    @Test
    void selectGoodsByCondition() {
    }
}