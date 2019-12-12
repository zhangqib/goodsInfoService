package xmu.oomall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xmu.oomall.Application;
import xmu.oomall.domain.po.BrandPo;

import java.time.LocalDateTime;

@SpringBootTest(classes = Application.class)
class BrandMapperTest {

    @Autowired
    private BrandMapper brandMapper;

    @Test
    void deleteByPrimaryKey() {
        System.out.println(brandMapper.deleteByPrimaryKey(3));
    }

    @Test
    void insert() {
        BrandPo brand = new BrandPo();
        brand.setName("test brand");
        brand.setDescription("test description");
        brand.setPicUrl("test pic url");
        brand.setGmtCreate(LocalDateTime.now());
        brand.setGmtModified(LocalDateTime.now());
        brand.setBeDeleted(false);
        brandMapper.insert(brand);
        System.out.println(brand);
    }

    @Test
    void selectByPrimaryKey() {
        System.out.println(brandMapper.selectByPrimaryKey(3));
    }

    @Test
    void updateByPrimaryKey() {
        BrandPo brand = brandMapper.selectByPrimaryKey(3);
        brand.setBeDeleted(true);
        System.out.println(brandMapper.updateByPrimaryKey(brand));
    }
}