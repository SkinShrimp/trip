package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.query.QueryObject;

import java.util.List;

public interface StrategyContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StrategyContent record);

    StrategyContent selectByPrimaryKey(Long id);

    List<StrategyContent> selectAll();

    int updateByPrimaryKey(StrategyContent record);

    List<StrategyContent> selectForList(QueryObject qo);
}