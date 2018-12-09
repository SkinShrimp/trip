package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyContent;

import java.util.List;

public interface IStrategyContentService {
    public abstract void save(StrategyContent entry);

    public abstract void delete(Long id);

    public abstract void update(StrategyContent entry);

    public abstract List<StrategyContent> listAll();

    public abstract StrategyContent get(Long id);

    List<StrategyContent> listByParentId(Long parentId);

    void deleteByparentId(Long id);
}
