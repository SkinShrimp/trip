package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.mapper.StrategyCatalogMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyCatalogServiceImpl implements IStrategyCatalogService {
    @Autowired
    private StrategyCatalogMapper strategyCatalogMapper;

    @Override
    public void save(StrategyCatalog entry) {
        strategyCatalogMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        strategyCatalogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(StrategyCatalog entry) {
        strategyCatalogMapper.updateByPrimaryKey(entry);

    }

    @Override
    public List<StrategyCatalog> listAll(Long strategyId) {
        List<StrategyCatalog> strategyCatalogs = strategyCatalogMapper.selectAll(strategyId);
        return strategyCatalogs;
    }


    @Override
    public PageInfo<StrategyCatalog> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<StrategyCatalog> list = strategyCatalogMapper.selectForList(qo);
        return new PageInfo<StrategyCatalog>(list);
    }

    @Override
    public StrategyCatalog get(Long id) {
        return null;
    }
}
