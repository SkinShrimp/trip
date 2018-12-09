package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrategyCatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StrategyCatalog record);

    StrategyCatalog selectByPrimaryKey(Long id);

    List<StrategyCatalog> selectAll(@Param("strategyId") Long strategyId);

    List<StrategyCatalog> selectForList(QueryObject qo);

    int updateByPrimaryKey(StrategyCatalog record);

}