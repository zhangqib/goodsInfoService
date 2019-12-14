package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.domain.po.GoodsCategoryPo;

import java.time.LocalDateTime;

@SpringBootTest
class GoodsCategoryMapperTest {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Test
    void deleteByPrimaryKey() {
        System.out.println(goodsCategoryMapper.deleteByPrimaryKey(3));
    }

    @Test
    void insert() {
        GoodsCategoryPo goodsCategory = new GoodsCategoryPo();
        goodsCategory.setName("test goods category");
        goodsCategory.setBeDeleted(false);
        goodsCategory.setPicUrl("test url");
        goodsCategory.setGmtCreate(LocalDateTime.now());
        goodsCategory.setGmtModified(LocalDateTime.now());
        goodsCategory.setPid(2);
        goodsCategoryMapper.insert(goodsCategory);
        System.out.println(goodsCategory);
    }

    @Test
    void selectByPrimaryKey() {
        System.out.println(goodsCategoryMapper.selectByPrimaryKey(3));
    }

    @Test
    void updateByPrimaryKey() {
        GoodsCategoryPo goodsCategory = goodsCategoryMapper.selectByPrimaryKey(3);
        goodsCategory.setName("test name");
        System.out.println(goodsCategoryMapper.updateByPrimaryKey(goodsCategory));
        selectByPrimaryKey();
    }
}