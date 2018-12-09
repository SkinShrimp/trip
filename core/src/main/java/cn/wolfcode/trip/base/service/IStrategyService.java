package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyService {
    public abstract void save(Strategy entry);

    public abstract void delete(Long id);

    public abstract void update(Strategy entry);

    public abstract List<Strategy> listAll(QueryObject qo);
    public abstract PageInfo<Strategy> query(QueryObject qo);

    public abstract Strategy get(Long id);
}
