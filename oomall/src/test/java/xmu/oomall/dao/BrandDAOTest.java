package xmu.oomall.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.domain.po.BrandPo;

@SpringBootTest
class BrandDAOTest {
    @Autowired
    private BrandDAO brandDAO;

    @Test
    @Transactional
    void insert() {

    }

    @Test
    @Transactional
    void deleteById() {

    }

    @Test
    void selectById() {
        Integer id = 71;
        Assert.notNull(brandDAO.selectById(71), "select brand failed");
    }

    @Test
    @Transactional
    void updateById() {
        Integer id = 71;
        BrandPo brand = brandDAO.selectById(id);
        brand.setName("test");
        brandDAO.updateById(brand);
        Assert.isTrue(brandDAO.selectById(id).getName().equals("test"), "update failed");
    }

    @Test
    void selectAll() {
        Assert.notEmpty(brandDAO.selectAll(1, 3), "select all brand failed");
    }

    @Test
    void selectBrandsByCondition() {
        Assert.notEmpty(brandDAO.selectBrandsByCondition(null, "刘伟", 1, 2),
                "select by condition failed");
    }
}