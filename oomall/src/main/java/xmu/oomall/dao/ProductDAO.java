package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.mapper.ProductMapper;

/**
 * @author Ke
 */
@Repository
public class ProductDAO {
    @Autowired
    private ProductMapper productMapper;
}
