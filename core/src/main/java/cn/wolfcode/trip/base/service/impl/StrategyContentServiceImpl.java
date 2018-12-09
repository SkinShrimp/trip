package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.service.IStrategyContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyContentServiceImpl implements IStrategyContentService {
    @Override
    public void save(StrategyContent entry) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(StrategyContent entry) {

    }

    @Override
    public List<StrategyContent> listAll() {
        return null;
    }

    @Override
    public StrategyContent get(Long id) {
        return null;
    }

    @Override
    public List<StrategyContent> listByParentId(Long parentId) {
        return null;
    }

    @Override
    public void deleteByparentId(Long id) {

    }
}
