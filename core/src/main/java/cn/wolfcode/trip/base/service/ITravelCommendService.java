package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import com.github.pagehelper.PageInfo;

public interface ITravelCommendService {
    public abstract void save(TravelCommend entry);

    public abstract void delete(Long id);

    public abstract void update(TravelCommend entry);

    public abstract TravelCommend get(Long id);

    public abstract PageInfo<TravelCommend> listAll(QueryObject qo);

    public abstract void saveOrUpdate(TravelCommend travelCommend);


    public abstract PageInfo<TravelCommend> getCommends(TravelCommendQueryObject qo);
}
