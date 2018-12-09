package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
//    @ResponseBody
    public String list(Model model, @ModelAttribute("qo") UserQueryObject qo) {
        JsonResult json = new JsonResult();
        model.addAttribute("pageInfo", userService.query(qo));
//        return json;
        return "user/list";
    }
}
