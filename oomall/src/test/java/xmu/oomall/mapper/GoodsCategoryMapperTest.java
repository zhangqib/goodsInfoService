package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.domain.po.GoodsCategoryPo;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class GoodsCategoryMapperTest {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Test
    void deleteByPrimaryKey() {
        Assert.isTrue(goodsCategoryMapper.deleteByPrimaryKey(123) == 1, "delete category failed");
    }

    @Test
    void insert() {
        GoodsCategoryPo goodsCategory = new GoodsCategoryPo();
        goodsCategory.setName("test goods category");
        goodsCategory.setBeDeleted(false);
        goodsCategory.setPicUrl("test url");
        goodsCategory.setGmtCreate(LocalDateTime.now());
        goodsCategory.setGmtModified(LocalDateTime.now());
        goodsCategoryMapper.insert(goodsCategory);
        Assert.notNull(goodsCategory.getId(), "insert failed");
    }

    @Test
    void selectByPrimaryKey() {
        Assert.notNull(goodsCategoryMapper.selectByPrimaryKey(123), "select category failed");
    }

    @Test
    void selectOneLevelGoodsCategories() {
        List<GoodsCategoryPo> goodsCategoryPos = goodsCategoryMapper.selectOneLevelGoodsCategories();
        Assert.notEmpty(goodsCategoryPos, "select one level category failed");
        goodsCategoryPos.forEach(goodsCategoryPo -> {
            Assert.notNull(goodsCategoryPo, "failed");
        });
    }

    @Test
    void selectSecondLevelGoodsCategories() {
        List<GoodsCategoryPo> goodsCategoryList = goodsCategoryMapper.selectSecondLevelGoodsCategories(122);
        Assert.notEmpty(goodsCategoryList, "select second categories failed");
        goodsCategoryList.forEach(goodsCategoryPo ->
            Assert.notNull(goodsCategoryPo, "failed")
        );
    }

    @Test
    void updateByPrimaryKey() {
        GoodsCategoryPo goodsCategory = goodsCategoryMapper.selectByPrimaryKey(122);
        goodsCategory.setName("test name");
        Assert.isTrue(goodsCategoryMapper.updateByPrimaryKey(goodsCategory) == 1, "update failed");
        Assert.isTrue(goodsCategoryMapper.selectByPrimaryKey(122).getName().equals("test name"), "update error");
    }
}