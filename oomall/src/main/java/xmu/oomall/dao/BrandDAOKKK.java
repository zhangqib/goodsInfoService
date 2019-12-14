package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Brand;
import xmu.oomall.mapper.BrandMapper;

import java.util.List;

/**
 * @author zhang
 */
@Repository
public class BrandDAOKKK {

    public Brand selectById(Integer id) {
        return null;
    }

    public List<Brand> selectBrandsByCondition(String brandId, String brandName, Integer page, Integer limit) {
        return null;
    }

    public Brand insert(Brand brand) {
        return null;
    }

    public Brand updateById(Brand brand) {
        return null;
    }

    public Integer deleteById(Integer id) {
        return -1;
    }
}