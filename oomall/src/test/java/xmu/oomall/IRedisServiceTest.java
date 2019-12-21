package xmu.oomall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import xmu.oomall.domain.po.GoodsPo;

import java.util.Arrays;

@SpringBootTest
class IRedisServiceTest {

    @Autowired
    private IRedisService iRedisService;

    @Test
    public void set() {
       Long existTime = 10L;
       GoodsPo goods = new GoodsPo();
       goods.setId(3);
       goods.setGoodsSn("goods sn");
       String testKey = "Goods:Id:"+goods.getId();
       iRedisService.set(testKey, goods, existTime);
       System.out.println(iRedisService.get(testKey));
    }

    @Test
    public void get() {
        Assert.isTrue(iRedisService.get("test key").equals("test value"), "redis get failed");
    }

    @Test
    public void sadd() {
        String testSet = "test set";
        String[] testVs = {"test v1", "test v2"};
        iRedisService.sadd(testSet, Arrays.asList(testVs), 10L);
        Assert.notEmpty(iRedisService.sget(testSet), "sadd failed");
    }
}