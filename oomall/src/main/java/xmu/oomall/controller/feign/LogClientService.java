package xmu.oomall.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xmu.oomall.domain.Log;


/**
 * @author Apophis
 */
@FeignClient(value = "LOGSERVICE")
public interface LogClientService {
    /**
     * 1.添加日志
     *
     * @param log
     * @return
     */
    @PostMapping("/log")
    public Object addLog(@RequestBody Log log);
}
