package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ITravelService travelService;

    @PostMapping
    public JsonResult register(User user) {
        JsonResult json = new JsonResult();
        try {
            userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            //设置错误信息
            json.mark(e.getMessage());
        }
        return json;
    }

    @PutMapping
    public JsonResult update(User user) {
        JsonResult json = new JsonResult();
        userService.update(user);
        json.setResult(user);
        return json;
    }


    @GetMapping("/{authorId}/travel")
    public PageInfo<Travel> queryForTravel(TravelQueryObject qo) {
        //设置查询条件
        qo.setOrderBy("t.lastUpdateTime DESC");
        return travelService.listAll(qo);
    }
}
