package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.mapper.TravelContentMapper;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * service公用TravelMapper和TravelContentMapper
 */
@Service
public class TravelServiceImpl implements ITravelService {
    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private TravelContentMapper travelContentMapper;

    @Override
    public void save(Travel entry) {
        travelMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        travelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Travel entry) {
        travelMapper.updateByPrimaryKey(entry);
    }

    @Override
    public Travel get(Long id) {
        return travelMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Travel> listAll(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<Travel> list = travelMapper.selectForList(qo);
        return new PageInfo<Travel>(list);
    }

    @Override
    public void saveOrUpdate(Travel travel) {
        Date date = new Date();
        travel.setLastupdateTime(date);
        //取出游记内容对象
        TravelContent travelContent = travel.getTravelContent();
        if (travel.getId() != null) {
            travelMapper.updateByPrimaryKey(travel);

            //更新游记
            travelContent.setId(travel.getId());
            travelContent.setContent(travel.getTravelContent().getContent());
            travelContentMapper.updateByPrimaryKey(travelContent);
        } else {
            travel.setAuthor(UserContext.getCurrentUser());
            travel.setCreateTime(date);
            travelMapper.insert(travel);

            //新增游记
            travelContent.setId(travel.getId());
            travelContent.setContent(travel.getTravelContent().getContent());
            travelContentMapper.insert(travelContent);
        }
    }

    @Override
    public void changeStateById(Long id, Integer state) {
        travelMapper.updateStateById(id, state);
    }

    @Override
    public TravelContent getTravelContent(Long id) {
        return travelContentMapper.selectByPrimaryKey(id);
    }
}
