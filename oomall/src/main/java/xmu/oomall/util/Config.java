package xmu.oomall.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Config {
    @Value("${oomall.redisexpiretime}")
    private Long redisExpireTime;
}
