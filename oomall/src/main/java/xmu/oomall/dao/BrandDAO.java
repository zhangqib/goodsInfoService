package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Brand;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.mapper.BrandMapper;
import xmu.oomall.mapper.GoodsMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang
 */
@Repository
public class BrandDAO {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 将商品插入数据库
     *
     * @return 更新完id的商品
     */
    public Brand insert(Brand brand) {
        if (isArgsInvalid(brand)) {
            return null;
        }
        brandMapper.insert(brand);
        return brand;
    }

    /**
     * 根据id删除Goods，级联删除其对应的product
     *
     * @param id
     * @return 删除是否成功
     */
    public boolean deleteById(Integer id) {
        goodsMapper.cleanBrand(id);
        return brandMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 根据id返回对应的商品
     *
     * @param id
     * @return Goods
     */
    public Brand selectById(Integer id) {
        return (Brand) brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据参数的id更新对应的goods
     *
     * @return 更新是否成功
     */
    public Brand updateById(Brand brand) {
        if (isArgsInvalid(brand)) {
            return null;
        }
        if (brandMapper.updateByPrimaryKey(brand) == 0) {
            return null;
        }
        return (Brand) brandMapper.selectByPrimaryKey(brand.getId());
    }
    
    public List<Brand> selectAll(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return brands(brandMapper.selectAll());
    }

    private List<Brand> brands(List<BrandPo> brandPos) {
        List<Brand> brands = new ArrayList<>();
        for (BrandPo brandPo : brandPos) {
           brands.add((Brand)brandPo);
        }
        return brands;
    }
    
    private boolean isArgsInvalid(Brand brand) {
        return brand.isBeDeleted();
    }
}