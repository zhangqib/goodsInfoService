package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.IRedisService;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GoodsDAOTest {
    @Autowired
    private GoodsDAO goodsDAO;

    @Autowired
    private IRedisService iRedisService;

    @Test
    @Transactional
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
        System.out.println(goods.getId());
        Assert.notNull(goods.getId(), "insert fail");
    }

    @Test
    @Transactional
    void deleteById() {
        Assert.isTrue(goodsDAO.deleteById(333), "delete failed");
    }

    @Test
    @Transactional
    void deleteByIdFailed() {
        Assert.isTrue(!goodsDAO.deleteById(5), "delete error");
    }

    @Test
    void selectById() {
        Integer goodsId = 273;
        Assert.notNull(goodsDAO.selectById(goodsId), "select failed");
        Assert.notNull(iRedisService.get(GoodsPo.getRedisKey(goodsId)), "redis get failed");
    }

    @Test
    void selectByProductId() {
        Integer productId = 1;
        Integer goodsId = 273;
        GoodsPo goods = goodsDAO.selectByProductId(productId);
        Assert.notNull(goods, "select goods by product id failed");
        Assert.isTrue(iRedisService.exists(ProductPo.getGoodsRedisKey(productId)), "redis failed");
        assertEquals(goods.getId(), goodsId);
    }

    @Test
    void selectByCategoryId() {
        Integer categoryId = 128;
        Assert.notEmpty(goodsDAO.selectByCategoryId(categoryId, 1, 10), "select goods by category failed");
    }

    @Test
    void selectGoodsByCondition() {
        Assert.notEmpty(goodsDAO.selectByCondition(null, null, null, 3, 4), "select goods by condition failed");
    }

    @Test
    void selectForSaleByCondition() {
        System.out.println(goodsDAO.selectForSaleByCondition(null,null,1,10));
        Assert.notEmpty(goodsDAO.selectForSaleByCondition(null, null, 1, 10), "select goods by condition failed");
    }
}