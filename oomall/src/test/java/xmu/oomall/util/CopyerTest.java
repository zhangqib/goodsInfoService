package xmu.oomall.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.impl.GoodsImpl;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.mapper.GoodsMapper;

@SpringBootTest
class CopyerTest {
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void copy() throws IllegalAccessException {
        GoodsPo goodsPo = goodsMapper.selectByPrimaryKey(3);
        GoodsImpl goodsImpl = new GoodsImpl();
        Goods goods = new Goods();
        goodsPo.setGoodsSn("2");
        Copyer.Copy(goodsPo, goodsImpl);
        System.out.println(goodsImpl.toString());
    }
}