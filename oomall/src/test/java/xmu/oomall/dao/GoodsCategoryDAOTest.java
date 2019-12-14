package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.domain.GoodsCategory;

@SpringBootTest
class GoodsCategoryDAOTest {

    @Autowired
    private GoodsCategoryDAO goodsCategoryDAO;

    @Test
    void insert() {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setPid(12);
        System.out.println(goodsCategoryDAO.insert(goodsCategory));
    }

    @Test
    void deleteById() {
        System.out.println(goodsCategoryDAO.deleteById(2));
    }

    @Test
    void updateById() {
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