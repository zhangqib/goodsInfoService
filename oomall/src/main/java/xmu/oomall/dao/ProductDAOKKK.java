package xmu.oomall.dao;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.domain.Product;
import xmu.oomall.domain.po.ProductPo;
import xmu.oomall.mapper.ProductMapper;

import java.util.List;

/**
 * @author zhang
 */
@Repository
public class ProductDAOKKK {

    public Product selectById(Integer id) {
        return null;
    }

    public List<Product> selectByGoodsId(Integer id, Integer page, Integer limit) {
        return null;
    }

    public Product insert(Product product) {
        return null;
    }

    public Product updateById(Product product) {
        return null;
    }

    public Integer deleteById(Integer id) {
        return -1;
    }
}
