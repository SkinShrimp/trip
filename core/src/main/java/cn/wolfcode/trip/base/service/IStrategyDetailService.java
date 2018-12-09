package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyDetailService {
    public abstract void save(StrategyDetail entry);

    public abstract void delete(Long id);

    public abstract void update(StrategyDetail entry);

    public abstract List<StrategyDetail> listAll();

    public abstract PageInfo<StrategyDetail> query(QueryObject qo);

    public abstract StrategyDetail get(Long id);
}
