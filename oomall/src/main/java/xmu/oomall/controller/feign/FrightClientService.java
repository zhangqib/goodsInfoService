package xmu.oomall.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhang
 */
@FeignClient(value="FRIGHTSERVICE")
public interface FrightClientService {
    /**
     * 获取运费模板对象
     *
     * @param id 商品id
     * @return http返回报文，“data”是待解析的运费模板对象
     */
    @GetMapping("/goods/{id}")
    public Object getFrightById(@PathVariable Integer id);
}
