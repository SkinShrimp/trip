package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/travelCommend")
public class TravelCommendController {
    @Autowired
    private ITravelCommendService travelCommendService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") TravelCommendQueryObject qo) {
        model.addAttribute("pageInfo", travelCommendService.listAll(qo));
        return "travelCommend/CommendList";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TravelCommend get(@PathVariable Long id) {
        return travelCommendService.get(id);
    }

    @ResponseBody
    @RequestMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(TravelCommend travelCommend, MultipartFile file) {
        //上传图片
        if (file != null && file.getSize() > 0) {
            String uploadUrl = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            travelCommend.setCoverUrl(uploadUrl);
        }
        travelCommendService.saveOrUpdate(travelCommend);
        return new JsonResult();
    }

}
