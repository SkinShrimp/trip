package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private IUserService userService;

    @PostMapping
    public JsonResult login(User user) {
        JsonResult json = new JsonResult();
        try {
            User login = userService.login(user);
            //返回给前台当前用户的信息
            json.setResult(login);
        } catch (Exception e) {
            e.printStackTrace();
            json.mark(e.getMessage());
        }

        return json;
    }

    @DeleteMapping
    public void loginOut() {
        UserContext.setCurrentUser(null);
    }
}
