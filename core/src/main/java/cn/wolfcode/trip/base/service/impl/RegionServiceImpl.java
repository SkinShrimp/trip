package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.mapper.RegionMapper;
import cn.wolfcode.trip.base.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public void save(Region entry) {
        regionMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        regionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Region entry) {
        regionMapper.updateByPrimaryKey(entry);
    }

    @Override
    public List<Region> listAll() {
        return regionMapper.selectAll();
    }

    @Override
    public Region get(Long id) {
        return regionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Region> listByParentId(Long parentId) {
        return regionMapper.selectByParentId(parentId);
    }

    @Override
    public void deleteByparentId(Long id) {
        regionMapper.deleteByParentId(id);
    }
}
