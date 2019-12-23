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
        ProductPo product = productDAO.selectById(12);
        Assert.isTrue(productDAO.deleteById(product), "delete failed");
        Assert.isTrue(productDAO.selectById(product.getId()).getBeDeleted(), "delete error");
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
        Integer productId = 407;
        int descStock = 6;
        ProductPo productPo = productDAO.selectById(productId);
        System.out.println(productPo.getSafetyStock());
        Assert.isTrue(productDAO.descStock(productId, descStock), "desc stock failed");
        System.out.println(productPo.getSafetyStock());
    }
    @Transactional
    @Test
    void incrStock() {
        Integer productId = 6;
        int descStock = 6;
        ProductPo productPo = productDAO.selectById(productId);
        System.out.println(productPo.getSafetyStock());
        Assert.isTrue(productDAO.incrStock(productId, descStock), "desc stock failed");
        System.out.println(productPo.getSafetyStock());
    }
}