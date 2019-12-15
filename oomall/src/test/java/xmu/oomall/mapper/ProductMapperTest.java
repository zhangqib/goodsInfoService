package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.Application;
import xmu.oomall.domain.Product;
import xmu.oomall.domain.po.ProductPo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Application.class)
class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    void deleteByPrimaryKey() {
        System.out.println(productMapper.deleteByPrimaryKey(22));
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
        productMapper.insert(product);
        System.out.println(product);
    }

    @Test
    void insertProducts() {
        List<ProductPo> products = new ArrayList<ProductPo>();
        for(int i = 0; i < 10; ++i) {
            ProductPo product = new ProductPo();
            product.setGoodsId(3);
            products.add(product);
        }
        System.out.println(productMapper.insertProducts(products));
    }

    @Test
    void selectByPrimaryKey() {
        System.out.println(productMapper.selectByPrimaryKey(24));
    }

    @Test
    void selectByGoodsId() {
       productMapper.selectByGoodsId(3).forEach(productPo -> System.out.println(productPo));
    }

    @Test
    void updateByPrimaryKey() {
//        ProductPo product = productMapper.selectByPrimaryKey(3);
        Product product = new Product();
        product.setId(6);
        product.setSafetyStock(12);
        System.out.println(productMapper.updateByPrimaryKey(product));
    }

}