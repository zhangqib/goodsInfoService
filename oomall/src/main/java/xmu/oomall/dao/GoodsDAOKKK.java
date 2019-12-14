package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Goods;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.GoodsMapper;
import xmu.oomall.mapper.ProductMapper;
import xmu.oomall.util.Copyer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang
 */
@Repository
public class GoodsDAOKKK {

    public Goods selectById(Integer id) {
        return null;
    }

    public List<Goods> selectByCondition(String goodsSn, String goodsName, Integer status, Integer page, Integer limit) {
    return null;
    }

    public List<Goods> selectByCategoryId(Integer id, Integer page, Integer limit) {
        return null;
    }

    public List<Goods> selectByBrandId(Integer id, Integer page, Integer limit) {
        return null;
    }

    public Goods insert(Goods goods) {
        return null;
    }

    public Goods updateById(Goods goods) {
        return null;
    }

    public Integer deleteById(Integer id) {
        return -1;
    }
}
