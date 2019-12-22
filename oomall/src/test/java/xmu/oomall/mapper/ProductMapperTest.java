package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.Application;
import xmu.oomall.domain.po.ProductPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Application.class)
@Transactional
class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    void deleteByPrimaryKey() {
        Integer id = 12;
        Assert.isTrue(productMapper.deleteByPrimaryKey(id) == 1, "delete failed!");
        Assert.isTrue(productMapper.selectByPrimaryKey(id).getBeDeleted(), "delete failed");
    }

    @Test
    void insert() {
        ProductPo product = new ProductPo();
        product.setBeDeleted(false);
        product.setGmtCreate(LocalDateTime.now());
        product.setGoodsId(5);
        product.setPicUrl("test url");
        product.setPrice(new BigDecimal(21));
        product.setSpecifications("test sp");
        product.setGmtModified(LocalDateTime.now());
        Assert.isTrue(productMapper.insert(product) == 1, "insert failed");
        Assert.notNull(productMapper.selectByPrimaryKey(product.getId()), "get id failed");
    }

    @Test
    void insertProducts() {
        List<ProductPo> products = new ArrayList<>();
        int productNum = 10;
        for(int i = 0; i < productNum; ++i) {
            ProductPo product = new ProductPo();
            product.setGoodsId(3);
            products.add(product);
        }
        Assert.isTrue(productMapper.insertProducts(products) == productNum, "insert products failed");
    }

    @Test
    void selectByPrimaryKey() {
        Integer id = 1;
        Assert.notNull(productMapper.selectByPrimaryKey(id), "select product failed");
    }

    @Test
    void selectEmptyProductsByGoodsId() {
        Integer goodsId = 274;
        Assert.noNullElements(productMapper.selectByGoodsId(goodsId), "select products(empty) by goodsId failed");
    }

    @Test
    void selectNotEmptyProductsByGoodsId() {
       Integer goodsId = 273;
       Assert.notEmpty(productMapper.selectByGoodsId(goodsId), "select products(not empty) by goodsId failed");
    }

    @Test
    void updateByPrimaryKeySuccess() {
        Integer productId = 12;
        ProductPo product = new ProductPo();
        product.setId(productId);
        product.setPicUrl("test url");
        Assert.isTrue(productMapper.updateByPrimaryKey(product) == 1, "update failed");
        Assert.isTrue(productMapper.selectByPrimaryKey(productId).getPicUrl().equals("test url"), "update failed");
    }

    @Test
    void updateByPrimaryKeyFailed() {
        Integer productId = 12;
        ProductPo product = new ProductPo();
        product.setId(productId);
        product.setPicUrl("test url");
        LocalDateTime localDateTime = LocalDateTime.now();
        product.setGmtCreate(localDateTime);
        Assert.isTrue(productMapper.updateByPrimaryKey(product) == 1, "update failed");
        Assert.isTrue(productMapper.selectByPrimaryKey(productId).getGmtCreate() != localDateTime, "update failed");
    }
}