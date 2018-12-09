package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements IStrategyService {
    @Autowired
    private StrategyMapper strategyMapper;

    @Override
    public void save(Strategy entry) {
        strategyMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        strategyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Strategy entry) {
        strategyMapper.updateByPrimaryKey(entry);
    }

    @Override
    public List<Strategy> listAll(QueryObject qo) {
        return strategyMapper.selectAll(qo);
    }

    @Override
    public PageInfo<Strategy> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<Strategy> list = strategyMapper.selectForList(qo);
        return new PageInfo<Strategy>(list);
    }

    @Override
    public Strategy get(Long id) {
        return strategyMapper.selectByPrimaryKey(id);
    }
}
