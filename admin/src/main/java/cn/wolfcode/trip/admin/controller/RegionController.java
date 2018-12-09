package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list() {
        return "region/list";
    }

    @RequestMapping("/listByParentId")
    @ResponseBody
    public List listByparentId(Long parentId, String type) {
        List<Region> list = regionService.listByParentId(parentId);
        if ("tree".equals(type)) {
            List<Map<String, Object>> treeList = new ArrayList<>();
            for (Region region : list) {
                treeList.add(region.toTreeMap());
            }
            return treeList;
        } else {
            return list;
        }
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Region region) {
        JsonResult json = new JsonResult();
        if (region.getId() != null) {
            regionService.update(region);
        } else {
            regionService.save(region);
        }
        return json;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        JsonResult json = new JsonResult();
        if(id != null){
            regionService.delete(id);

            //删除子节点
            regionService.deleteByparentId(id);
        }
        return json;
    }
}
