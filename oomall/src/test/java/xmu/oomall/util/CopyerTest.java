package xmu.oomall.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.mapper.GoodsMapper;

@SpringBootTest
class CopyerTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void copy() {
        GoodsPo goodsPo = goodsMapper.selectByPrimaryKey(3);
        goodsPo.setGoodsSn("2");
        goodsPo = null;
        Goods goods = new Goods();
        if (Copyer.Copy(goodsPo, goods)) {
            System.out.println(goods);
        }
        System.out.println("null");
    }
}