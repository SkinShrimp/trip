package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    private IRegionService regionService;

    @GetMapping
    public List<Region> list() {
        return regionService.listAll();
    }
}
