package xmu.oomall.domain.po;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xmu.oomall.domain.GoodsCategory;

import java.time.LocalDateTime;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:商品种类的信息
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GoodsCategoryPo {
    private Integer id;
    /**
     * 种类的名称
     */
    private String name;
    /**
     * 该种类的父种类ID
     */
    private Integer pid;
    /**
     * 二级目录的pic
     */
    private String picUrl;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

    public GoodsCategoryPo() {
        this.id = null;
        this.name = null;
        this.pid = null;
        this.picUrl = null;
        this.gmtCreate = null;
        this.gmtModified = null;
        this.beDeleted = null;
    }

    public GoodsCategoryPo(GoodsCategoryPo goodsCategory) {
        this.id = goodsCategory.getId();
        this.name = goodsCategory.getName();
        this.pid = goodsCategory.getPid();
        this.picUrl = goodsCategory.getPicUrl();
        this.gmtCreate = goodsCategory.getGmtCreate();
        this.gmtModified = goodsCategory.getGmtModified();
        this.beDeleted = goodsCategory.getBeDeleted();
    }
    public GoodsCategoryPo(GoodsCategory goodsCategory) {
        this.id = goodsCategory.getId();
        this.name = goodsCategory.getName();
        this.pid = goodsCategory.getPid();
        this.picUrl = goodsCategory.getPicUrl();
        this.gmtCreate = goodsCategory.getGmtCreate();
        this.gmtModified = goodsCategory.getGmtModified();
        this.beDeleted = goodsCategory.getBeDeleted();
    }
}
