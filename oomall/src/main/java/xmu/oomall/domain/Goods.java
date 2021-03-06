package xmu.oomall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.domain.po.GoodsCategoryPo;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:商品对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Goods extends GoodsPo {
    private BrandPo brandPo;
    private GoodsCategoryPo goodsCategoryPo;
    private List<ProductPo> productPoList;
    private GrouponRule grouponRule;
    private ShareRule shareRule;
    private PresaleRule presaleRule;


    public Goods() {
        super();
    }

    public Goods(Goods goods) {
        super(goods);
    }

    public Goods(GoodsPo goods) {
        super(goods);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setModified() {
        this.setGmtModified(LocalDateTime.now());
    }

    public boolean insertAble() {
        if (this.getBeDeleted()) {
            return false;
        }
        if (this.getBrandId() != null && brandPo == null) {
            return false;
        }
        return this.getGoodsCategoryId() == null || goodsCategoryPo != null;
    }
}
