package xmu.oomall.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import xmu.oomall.domain.Log;


@FeignClient(value = "LOGSERVICE")
public interface LogClientService {
    @PostMapping("/logs")
    public Object addLog(Log log);
}
