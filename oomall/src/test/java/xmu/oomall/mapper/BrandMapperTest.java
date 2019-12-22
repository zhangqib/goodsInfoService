package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import xmu.oomall.Application;
import xmu.oomall.domain.po.BrandPo;

import java.time.LocalDateTime;

@SpringBootTest(classes = Application.class)
@Transactional
class BrandMapperTest {

    @Autowired
    private BrandMapper brandMapper;

    @Test
    void deleteByPrimaryKey() {
        Assert.isTrue(brandMapper.deleteByPrimaryKey(72) == 1, "delete brand failed");
    }

    @Test
    void deleteByPrimaryKeyFail() {
        Integer brandId = 71;
        Assert.isTrue(brandMapper.deleteByPrimaryKey(brandId) == 0, "delete brand error");
        Assert.isTrue(brandMapper.selectByPrimaryKey(brandId).isBeDeleted(), "delete error");
//        Assert.isTrue(brandMapper.deleteByPrimaryKey(77) == 0, "delete brand error");
    }

    @Test
    void insert() {
        BrandPo brand = new BrandPo();
        brand.setName("test brand");
        brand.setDescription("test description");
        brand.setPicUrl("test pic url");
        brand.setGmtCreate(LocalDateTime.now());
        brand.setGmtModified(LocalDateTime.now());
        Assert.isTrue(brandMapper.insert(brand) == 1, "insert brand failed");
    }

    @Test
    void selectByPrimaryKey() {
        Assert.notNull(brandMapper.selectByPrimaryKey(71), "select brand failed");
    }

    @Test
    void selectAll() {
        Assert.notEmpty(brandMapper.selectAll(), "select all brand failed");
        brandMapper.selectAll().forEach(
                brandPo -> Assert.isTrue(!brandPo.isBeDeleted(), "get wrong brand in select all")
        );
    }

    @Test
    void selectByCondition() {
        Assert.notEmpty(brandMapper.selectByCondition(null, "刘伟"), "select by conditon failed");
    }

    @Test
    void updateByPrimaryKey() {
        BrandPo brand = new BrandPo();
        Integer brandId = 71;
        String name = "update brand test";
        brand.setId(brandId);
        brand.setName(name);
        Assert.isTrue(brandMapper.updateByPrimaryKey(brand) == 1, "update brand failed");
        Assert.isTrue(brandMapper.selectByPrimaryKey(brandId).getName().equals(name), "update brand name failed");
    }
}