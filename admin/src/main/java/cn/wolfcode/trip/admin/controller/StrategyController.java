package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") StrategyQueryObject qo) {

        model.addAttribute("pageInfo", strategyService.query(qo));
        model.addAttribute("regions", regionService.listAll());
        return "strategy/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Strategy strategy, MultipartFile file) {
        //上传图片
        if (file != null && file.getSize() > 0) {
            String uploadPath = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            strategy.setCoverUrl(uploadPath);
        }
        if (strategy.getId() != null) {
            strategyService.update(strategy);
        } else {
            strategyService.save(strategy);
        }
        return new JsonResult();

    }
}
