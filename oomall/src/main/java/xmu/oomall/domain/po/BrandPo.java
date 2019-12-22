package xmu.oomall.domain.po;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xmu.oomall.domain.Brand;

import java.time.LocalDateTime;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:品牌对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BrandPo {

    private Integer id;
    private String description;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private boolean beDeleted;
    private String name;
    private String picUrl;

    public BrandPo() {
        this.id = null;
        this.description = null;
        this.gmtCreate = null;
        this.gmtModified = null;
        this.beDeleted = false;
        this.name = null;
        this.picUrl = null;
    }

    public BrandPo(BrandPo brand) {
        this.id = brand.getId();
        this.description = brand.getDescription();
        this.gmtCreate = brand.getGmtCreate();
        this.gmtModified = brand.getGmtModified();
        this.beDeleted = brand.isBeDeleted();
        this.name = brand.getName();
        this.picUrl = brand.getPicUrl();
    }

    public BrandPo(Brand brand) {
        this.id = brand.getId();
        this.description = brand.getDescription();
        this.gmtCreate = brand.getGmtCreate();
        this.gmtModified = brand.getGmtModified();
        this.beDeleted = brand.isBeDeleted();
        this.name = brand.getName();
        this.picUrl = brand.getPicUrl();
    }
}
