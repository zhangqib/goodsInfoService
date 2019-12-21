package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.IRedisService;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
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
        ProductPo product = productDAO.selectById(1);
        Assert.notNull(product, "select failed");
        Assert.notNull(iRedisService.get(product.getRedisKey()), "redis get failed");
    }
    @Test
    void selectByIdFailed() {
        ProductPo product = productDAO.selectById(11111);
        Assert.isNull(product, "select failed");
    }

    @Test
    void selectByGoodsId() {
        Integer goodsId = 273;
        Assert.notEmpty(productDAO.selectByGoodsId(goodsId, 1, 1), "select products failed");
        System.out.println(iRedisService.sget(GoodsPo.getProductRedisKeys(goodsId)));
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