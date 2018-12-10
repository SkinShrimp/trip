package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ITagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public void save(Tag entry) {
        tagMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Tag entry) {
        tagMapper.updateByPrimaryKey(entry);
    }

    @Override
    public List<Tag> listAll() {
        return tagMapper.selectAll();
    }

    @Override
    public Tag get(Long id) {
        return null;
    }

    @Override
    public PageInfo<Tag> queryTop(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<Tag> list = tagMapper.selectTop(qo);
        return new PageInfo<Tag>(list);
    }
}
