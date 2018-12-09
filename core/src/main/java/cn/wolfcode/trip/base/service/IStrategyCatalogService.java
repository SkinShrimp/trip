package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyCatalogService {
    public abstract void save(StrategyCatalog entry);

    public abstract void delete(Long id);

    public abstract void update(StrategyCatalog entry);

    public abstract List<StrategyCatalog> listAll(Long strategyId);

    public abstract PageInfo<StrategyCatalog> query(QueryObject qo);

    public abstract StrategyCatalog get(Long id);
}
