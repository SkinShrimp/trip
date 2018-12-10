package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.*;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TagQueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.*;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/strategies")
public class StrategyController {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private ITravelCommendService travelCommendService;
    @Autowired
    private IRegionService regionService;
    @Autowired
    private ServletContext servletContext;

    @Autowired
    private IStrategyDetailService strategyDetailService;
    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyCommentService strategyCommentService;
    @Autowired
    private ITagService tagService;

    @GetMapping
    public PageInfo<TravelCommend> list(TravelCommendQueryObject qo) {
        //设置页面的大小为0，就会一次性都查出来
        qo.setPageSize(0);
        qo.setType(TravelCommend.COMMEND_TYPE);
        return travelCommendService.query(qo);
    }

    @GetMapping("/strategies")
    public PageInfo<Strategy> listStrategy(StrategyQueryObject qo) {
        qo.setState(Strategy.STATE_HOT);
        return strategyService.query(qo);
    }

    @GetMapping("/regions")
    public List<Region> listRegion() {
        return regionService.listRegionByState(Region.STATE_HOT);
    }

    @GetMapping("/regions/{regionId}/strategies")
    public void queryStrategiesByRegionId(@ModelAttribute("qo") StrategyQueryObject qo, HttpServletResponse response) throws IOException, TemplateException {
        //获取普通地区
        qo.setState(Strategy.STATE_NORMAL);
        PageInfo<Strategy> all = strategyService.query(qo);

        //获取热门地区的攻略
        qo.setPageSize(0);
        qo.setState(Strategy.STATE_HOT);
        PageInfo<Strategy> hot = strategyService.query(qo);

        //创建freewark的配置对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        //设置模板所在目录
        configuration.setDirectoryForTemplateLoading(new File(servletContext.getRealPath("/template")));
        //获取模板
        Template template = configuration.getTemplate("strategyTemplate.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("hots", hot.getList());
        map.put("alls", all.getList());
        response.setContentType("text/html;charset=utf-8");
        //数据和模板合成
        template.process(map, response.getWriter());
    }

    /**
     * 根据id获取大攻略内容
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Strategy getStrategyById(@PathVariable Long id) {
        return strategyService.get(id);
    }

    @GetMapping("/{strategyId}/catalogs")
    public List<StrategyCatalog> getStrategyCatalogs(@PathVariable Long strategyId) {
        return strategyCatalogService.listAll(strategyId);
    }

    @GetMapping("/details/{id}")
    public StrategyDetail getStrategyDetailById(@PathVariable Long id) {
        return strategyDetailService.get(id);
    }

    /**
     * 大攻略页面的分页查询
     *
     * @param qo
     * @return
     */
    @GetMapping("/split")
    public PageInfo<Strategy> getSplitStrategy(StrategyQueryObject qo) {
        return strategyService.query(qo);
    }

    @GetMapping("/{strategyId}/comments")
    public PageInfo<StrategyComment> queryComments(StrategyCommentQueryObject qo) {
        qo.setOrderBy("sc.createTime DESC");
        return strategyCommentService.query(qo);
    }

    @PostMapping("/{strategy.id}/comments")
    public JsonResult saveComment(StrategyComment strategyComment, String[] tags) {
        strategyCommentService.save(strategyComment, tags);
        return new JsonResult();
    }


    @GetMapping("/{strategyId}/tags")
    public PageInfo<Tag> getTags(TagQueryObject qo) {
        qo.setCurrentPage(6);
        return tagService.queryTop(qo);
    }
}
