package xmu.oomall.domain.po;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xmu.oomall.domain.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: 数据库与对象模型标准组
 * @Description: 产品信息
 * @Date: Created in 16:00 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductPo implements Serializable {

    private Integer id;
    /**
     * 产品对应商品的id
     */
    private Integer goodsId;
    /**
     * 产品图片的url
     */
    private String picUrl;
    /**
     * sku属性，用于描述特定货品，如红色，41码
     * JSON格式，针对不同规格有不同的描述
     * eg1. {"color": "red", "size": 41}，可以表示红色41码
     * eg2. {"color": "black", "volume": 500}，可以表示黑色500ml的水杯
     */
    private String specifications;
    /**
     * 产品价格
     */
    private BigDecimal price;
    /**
     * 产品安全库存
     */
    private Integer safetyStock;

    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

    public ProductPo() {
        this.id = null;
        this.goodsId = null;
        this.picUrl = null;
        this.specifications = null;
        this.price = null;
        this.safetyStock = null;
        this.gmtCreate = null;
        this.gmtModified = null;
        this.beDeleted = null;
    }

    public ProductPo(ProductPo productPo) {
        this.id = productPo.getId();
        this.goodsId = productPo.getGoodsId();
        this.picUrl = productPo.getPicUrl();
        this.specifications = productPo.getSpecifications();
        this.price = productPo.getPrice();
        this.safetyStock = productPo.getSafetyStock();
        this.gmtCreate = productPo.getGmtCreate();
        this.gmtModified = productPo.getGmtModified();
        this.beDeleted = productPo.getBeDeleted();
    }

    public ProductPo(Product productPo) {
        this.id = productPo.getId();
        this.goodsId = productPo.getGoodsId();
        this.picUrl = productPo.getPicUrl();
        this.specifications = productPo.getSpecifications();
        this.price = productPo.getPrice();
        this.safetyStock = productPo.getSafetyStock();
        this.gmtCreate = productPo.getGmtCreate();
        this.gmtModified = productPo.getGmtModified();
        this.beDeleted = productPo.getBeDeleted();
    }


    public String getRedisKey() {
        return "Product:Id:" + this.id;
    }

    public String getGoodsRedisKey() {
        return "Product:Id:" + this.id + ":Goods:Id";
    }

    static public String getRedisKey(Integer id) {
        return "Product:Id:" + id;
    }

    static public String getGoodsRedisKey(Integer id) {
        return "Product:Id:"+id+":Goods:Id";
    }
}