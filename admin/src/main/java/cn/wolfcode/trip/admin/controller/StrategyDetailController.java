package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
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
@RequestMapping("/strategyDetail")
public class StrategyDetailController {
    @Autowired
    private IStrategyDetailService StrategyDetailService;
    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") StrategyDetailQueryObject qo) {
        model.addAttribute("pageInfo", StrategyDetailService.query(qo));
        model.addAttribute("strategies", strategyService.listAll(new QueryObject()));
        return "strategyDetail/list";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyDetail strategyDetail, MultipartFile file) {
        if (file != null && file.getSize() > 0) {
            String url = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            strategyDetail.setCoverUrl(url);
        }
        StrategyDetailService.saveOrUpdate(strategyDetail);

        return new JsonResult();

    }

    @RequestMapping("/content")
    @ResponseBody
    public StrategyContent getContentByDetailId(Long id) {
        return StrategyDetailService.getContentByDetailId(id);
    }
}
