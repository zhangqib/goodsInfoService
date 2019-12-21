package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.IRedisService;
import xmu.oomall.domain.Product;
import xmu.oomall.mapper.ProductMapper;

@SpringBootTest
class ProductDAOTest {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IRedisService iRedisService;

    @Transactional
    @Test
    void insert() {
    }

    @Transactional
    @Test
    void deleteById() {
    }

    @Test
    void selectById() {
        Product product = productDAO.selectById(1);
        Assert.notNull(product, "select failed");
        Assert.notNull(iRedisService.get(product.getRedisKey()), "redis get failed");
    }
    @Test
    void selectByIdFailed() {
        Product product = productDAO.selectById(11111);
        Assert.isNull(product, "select failed");
    }

    @Test
    void selectByGoodsId() {
    }

    @Transactional
    @Test
    void updateById() {
    }

    @Transactional
    @Test
    void descStock() {
    }
}