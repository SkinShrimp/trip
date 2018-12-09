package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.mapper.StrategyDetailMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyDetailServiceImpl implements IStrategyDetailService {
    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    @Override
    public void save(StrategyDetail entry) {
        strategyDetailMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        strategyDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(StrategyDetail entry) {
        strategyDetailMapper.updateByPrimaryKey(entry);
    }

    @Override
    public List<StrategyDetail> listAll() {
        return strategyDetailMapper.selectAll();
    }


    @Override
    public PageInfo<StrategyDetail> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<StrategyDetail> list = strategyDetailMapper.selectForList(qo);
        return new PageInfo<StrategyDetail>(list);
    }


    @Override
    public StrategyDetail get(Long id) {
        return strategyDetailMapper.selectByPrimaryKey(id);
    }
}
