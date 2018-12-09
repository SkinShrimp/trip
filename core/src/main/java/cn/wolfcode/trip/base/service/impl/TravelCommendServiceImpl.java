package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.mapper.TravelCommendMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelCommendServiceImpl implements ITravelCommendService {
    @Autowired
    private TravelCommendMapper travelCommendMapper;

    @Override
    public void save(TravelCommend entry) {
        travelCommendMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        travelCommendMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(TravelCommend entry) {
        travelCommendMapper.updateByPrimaryKey(entry);

    }

    @Override
    public TravelCommend get(Long id) {
        return travelCommendMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<TravelCommend> listAll(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<TravelCommend> list = travelCommendMapper.selectAll(qo);
        return new PageInfo<TravelCommend>(list);
    }

    @Override
    public void saveOrUpdate(TravelCommend travelCommend) {
        if (travelCommend.getId() != null) {
            travelCommendMapper.updateByPrimaryKey(travelCommend);
        } else {
            travelCommendMapper.insert(travelCommend);
        }
    }

    @Override
    public PageInfo<TravelCommend> getCommends(TravelCommendQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<TravelCommend> list = travelCommendMapper.selectForAppList(qo);
        return new PageInfo<TravelCommend>(list);
    }

    @Override
    public PageInfo<TravelCommend> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<TravelCommend> list = travelCommendMapper.selectForAppList(qo);
        return new PageInfo<TravelCommend>(list);
    }
}
