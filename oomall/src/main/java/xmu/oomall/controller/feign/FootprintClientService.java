package xmu.oomall.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.domain.po.FootprintItemPo;

/**

 * @author LYD

 */

@FeignClient(value="FOOTPRINTSERVICE")

public interface FootprintClientService {
    @PostMapping("/footprints")
    Object addFootprint(@RequestBody FootprintItemPo footprintItemPo);
}