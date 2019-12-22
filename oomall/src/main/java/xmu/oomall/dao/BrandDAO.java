package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.mapper.BrandMapper;
import xmu.oomall.mapper.GoodsMapper;

import java.time.LocalDateTime;
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
    public BrandPo insert(BrandPo brand) {
        if (isArgsInvalid(brand)) {
            return null;
        }
        brand.setGmtCreate(LocalDateTime.now());
        brand.setGmtModified(LocalDateTime.now());
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
    public BrandPo selectById(Integer id) {
        return  brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据参数的id更新对应的goods
     *
     * @return 更新是否成功
     */
    public BrandPo updateById(BrandPo brand) {
        if (isArgsInvalid(brand)) {
            return null;
        }
        brand.setGmtModified(LocalDateTime.now());
        if (brandMapper.updateByPrimaryKey(brand) == 0) {
            return null;
        }
        return  brandMapper.selectByPrimaryKey(brand.getId());
    }
    
    public List<BrandPo> selectAll(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return brandMapper.selectAll();
    }

    private boolean isArgsInvalid(BrandPo brand) {
        return brand.isBeDeleted();
    }

    public List<BrandPo> selectBrandsByCondition(Integer brandId, String brandName, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return brandMapper.selectByCondition(brandId, brandName);
    }

}