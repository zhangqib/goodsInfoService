package xmu.oomall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xmu.oomall.domain.po.BrandPo;
import xmu.oomall.domain.po.GoodsPo;

import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:品牌对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Brand extends BrandPo {

    private List<GoodsPo> goodsPoList;

    public Brand() {
        super();
    }
    public Brand(BrandPo brand) {
        super(brand);
    }
    public Brand(Brand brand) {
        super(brand);
    }
}
