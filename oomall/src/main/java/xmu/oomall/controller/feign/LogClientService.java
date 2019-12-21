package xmu.oomall.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xmu.oomall.domain.Log;


@FeignClient(value = "LOGSERVICE")
public interface LogClientService {
    @PostMapping("/log")
    public Object addLog(@RequestBody Log log);
}
