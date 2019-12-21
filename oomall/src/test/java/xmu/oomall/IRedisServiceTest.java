package xmu.oomall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import xmu.oomall.dao.GoodsDAO;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.util.Config;

import java.util.Arrays;

@SpringBootTest
class IRedisServiceTest {

    @Autowired
    private IRedisService iRedisService;

    @Autowired
    private Config config;

    @Autowired
    private GoodsDAO goodsDAO;

    @Test
    public void set() {
       Long existTime = 10L;
       GoodsPo goods = new GoodsPo();
       goods.setId(3);
       goods.setGoodsSn("goods sn");
       String testKey = "Goods:Id:"+goods.getId();
       iRedisService.set(testKey, goods);
       System.out.println(iRedisService.get(testKey));
    }


    /**
     * get failed
     */
    @Test
    public void get() {
        ProductPo product = new ProductPo();
        product.setId(22);
        ProductPo productPo = (ProductPo) iRedisService.get(product.getRedisKey());
        Assert.isNull(productPo, "get fails");

    }

    @Test
    public void sadd() {
        String testSet = "test set";
        String[] testVs = {"test v1", "test v2"};
        iRedisService.sadd(testSet, Arrays.asList(testVs));
        Assert.notEmpty(iRedisService.sget(testSet), "sadd failed");
        System.out.println(iRedisService.sget(testSet));
    }
}