package xmu.oomall.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.oomall.mapper.BrandMapper;

/**
 * @author Ke
 */
@Repository
public class BrandDAO {
    @Autowired
    private BrandMapper brandMapper;
}
