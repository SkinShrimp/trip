package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travels")
public class TravelController {
    @Autowired
    private ITravelService travelService;
    @Autowired
    private ITravelCommendService travelCommendService;

    /**
     * 应该分开
     *
     * @param travel
     * @return
     */
    @PostMapping
    public JsonResult saveOrUpdate(Travel travel) {
        JsonResult json = new JsonResult();
        travelService.saveOrUpdate(travel);
        return json;
    }

    @GetMapping("/{id}")
    public Travel get(@PathVariable Long id) {
        return travelService.get(id);
    }

    @GetMapping()
    public PageInfo<Travel> list(TravelQueryObject qo) {
        return travelService.listAll(qo);
    }

    /**
     * 获取推荐(周、月)文章
     *
     * @return
     */
    @GetMapping("/commends")
    public PageInfo<TravelCommend> getCommends(TravelCommendQueryObject qo) {
        qo.setOrderBy("tc.schedule DESC");
        return travelCommendService.getCommends(qo);
    }
}
