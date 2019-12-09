package xmu.oomall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.oomall.dao.GoodsDAO;
import xmu.oomall.dao.ProductDAO;
import xmu.oomall.domain.Goods;

/**
 * @author zhang
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDAO goodsDAO;
    @Autowired
    private ProductDAO productDAO;

    public int deleteGoods(Goods goods) {

    }
}
