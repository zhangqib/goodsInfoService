package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.domain.po.GoodsCategoryPo;

@SpringBootTest
class GoodsCategoryDAOTest {

    @Autowired
    private GoodsCategoryDAO goodsCategoryDAO;

    @Test
    @Transactional
    void insert() {
        GoodsCategoryPo goodsCategory = new GoodsCategoryPo();
        goodsCategory.setBeDeleted(false);
        goodsCategory.setPid(12);
        System.out.println(goodsCategoryDAO.insert(goodsCategory));
    }

    @Test
    @Transactional
    void deleteById() {
        Assert.isTrue(goodsCategoryDAO.deleteById(123), "delete failed");
    }

    @Test
    @Transactional
    void deleteByIdFailed() {
        Assert.isTrue(!goodsCategoryDAO.deleteById(111), "delete error");
    }

    @Test
    @Transactional
    void updateById() {
        GoodsCategoryPo category = goodsCategoryDAO.selectById(122);
        String testName = "test";
        category.setName(testName);
        goodsCategoryDAO.updateById(category);
    }

    @Test
    void selectOneLevelGoodsCategories() {
    }

    @Test
    void selectSecondLevelGoodsCategories() {
    }

    @Test
    void selectGoodsCategoriesByCondition() {
    }
}