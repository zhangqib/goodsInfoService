package xmu.oomall.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Ke
 * @Description: GoodsCategoryController
 * @create 2019/12/20
 */
@Component
@Getter
public class Config {
    @Value("${oomall.redisexpiretime}")
    private Long redisExpireTime;

    @Value("${oomall.predescQty}")
    private Integer preDescQty;

    @Value(("${oomall.redisStockThreshold}"))
    private Integer redisStockThreshold;
}
