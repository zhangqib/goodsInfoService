package xmu.oomall.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xmu.oomall.domain.po.FootprintItemPo;

/**

 * @author LYD

 */

@FeignClient(value="FOOTPRINTSERVICE",url = "101.200.178.99:8010")
public interface FootprintClientService {
    /**
     * 1.增加用户浏览商品的足迹
     *
     * @param footprintItemPo
     * @return
     */
    @PostMapping("/footprints")
    Object addFootprint(@RequestBody FootprintItemPo footprintItemPo);
}