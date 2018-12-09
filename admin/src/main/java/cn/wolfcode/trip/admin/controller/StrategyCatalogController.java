package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/strategyCatalog")
public class StrategyCatalogController {
    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") StrategyCatalogQueryObject qo) {
        model.addAttribute("pageInfo", strategyCatalogService.query(qo));
        model.addAttribute("strategys", strategyService.listAll(new QueryObject()));
        return "strategyCatalog/list";
    }

    @RequestMapping("/catalogs")
    @ResponseBody
    public List getCatalogsByStrategyId(Long strategyId) {
        return strategyCatalogService.listAll(strategyId);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyCatalog strategyCatalog) {
        if (strategyCatalog.getId() != null) {
            strategyCatalogService.update(strategyCatalog);
        } else {
            strategyCatalogService.save(strategyCatalog);
        }
        return new JsonResult();

    }
}
