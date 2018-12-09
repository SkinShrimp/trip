package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface ITravelService {
    public abstract void save(Travel entry);

    public abstract void delete(Long id);

    public abstract void update(Travel entry);

    public abstract Travel get(Long id);

    public abstract PageInfo<Travel> listAll(QueryObject qo);

    public abstract void saveOrUpdate(Travel travel);

    public abstract void changeStateById(Long id, Integer state);

    public abstract TravelContent getTravelContent(Long id);
}

