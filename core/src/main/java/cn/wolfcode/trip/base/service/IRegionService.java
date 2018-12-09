package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Region;

import java.util.List;

public interface IRegionService {
    public abstract void save(Region entry);

    public abstract void delete(Long id);

    public abstract void update(Region entry);

    public abstract List<Region> listAll();

    public abstract Region get(Long id);

    List<Region> listByParentId(Long parentId);

    void deleteByparentId(Long id);

    List<Region> listRegionByState(int stateHot);
}
