package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ITagService {
    public abstract void save(Tag entry);

    public abstract void delete(Long id);

    public abstract void update(Tag entry);

    public abstract List<Tag> listAll();

    public abstract Tag get(Long id);

    PageInfo<Tag> queryTop(QueryObject qo);
}
