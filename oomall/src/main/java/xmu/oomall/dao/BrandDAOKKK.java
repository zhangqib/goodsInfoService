package xmu.oomall.dao;

import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Brand;

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

    public Boolean deleteById(Integer id) {
        return true;
    }
}