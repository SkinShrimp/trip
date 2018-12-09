package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyContentService {
    public abstract void save(StrategyContent entry);

    public abstract void delete(Long id);

    public abstract void update(StrategyContent entry);

    public abstract List<StrategyContent> listAll();

    public abstract StrategyContent get(Long id);

    public abstract PageInfo<StrategyContent> query(QueryObject qo);

}
