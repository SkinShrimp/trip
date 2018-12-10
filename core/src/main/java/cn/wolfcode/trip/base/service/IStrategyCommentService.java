package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyCommentService {
    public abstract void save(StrategyComment entry, String[] tags);

    public abstract void delete(Long id);

    public abstract void update(StrategyComment entry);

    public abstract List<StrategyComment> listAll();

    public abstract PageInfo<StrategyComment> query(QueryObject qo);

}
