package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    private ITravelService travelService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") TravelQueryObject qo) {

        if (qo.getState() == null) {
            qo.setState(Travel.STATE_VERIFY);
        }
        //排序
        qo.setOrderBy("t.lastUpdateTime DESC");
        model.addAttribute("pageInfo", travelService.listAll(qo));

        return "travel/list";
    }

    @RequestMapping("/releaseList")
    public String releaseList(Model model, @ModelAttribute("qo") TravelQueryObject qo) {
        //只查询已经发布的游记
        qo.setState(Travel.STATE_RELEASE);
        //排序(按照发布时间降序)
        qo.setOrderBy("t.releaseTime DESC");
        model.addAttribute("pageInfo", travelService.listAll(qo));

        return "travelCommend/list";
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public JsonResult changeState(Long id, Integer state) {
        travelService.changeStateById(id, state);
        return new JsonResult();
    }

    @RequestMapping("/get")
    @ResponseBody
    public TravelContent getTravelContent(Long id) {
        return travelService.getTravelContent(id);
    }


}

