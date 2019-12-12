package xmu.oomall.dao;

import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Brand;

import java.util.List;

/**
 * @author zhang
 */
@Repository
public class BrandDAO {
    /**
     * 将商品插入数据库
     *
     * @param goods
     * @return 更新完id的商品
     */
    public Brand insert(Brand brand) {
        return null;
    }

    /**
     * 根据id删除Goods，级联删除其对应的product
     *
     * @param id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        return false;
    }

    /**
     * 根据id返回对应的商品
     *
     * @param id
     * @return Goods
     */
    public Brand selectById(Integer id) {
        return null;
    }

    /**
     * 根据参数的id更新对应的goods
     *
     * @param goods
     * @return 更新是否成功
     */
    public boolean updateById(Brand brand) {
        return true;
    }

    public List<Brand> selectAllBrand() {
        return null;
    }
}