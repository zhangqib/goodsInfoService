package xmu.oomall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xmu.oomall.domain.po.GoodsPo;
import xmu.oomall.domain.po.ProductPo;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:产品对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Product extends ProductPo {
    private GoodsPo goodsPo;

    public Product() {
        super();
    }
    public Product(ProductPo productPo) {
        super(productPo);
    }
    public Product(Product productPo) {
        super(productPo);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
