package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.mapper.StrategyCommentMapper;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyCommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StrategyCommentServiceImpl implements IStrategyCommentService {
    @Autowired
    private StrategyCommentMapper strategyCommentMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void save(StrategyComment entry, String[] tags) {
        if (entry.getState() == StrategyComment.STATE_HOT) {
            entry.setCommendTime(new Date());
        }
        entry.setUser(UserContext.getCurrentUser());
        entry.setCreateTime(new Date());
        strategyCommentMapper.insert(entry);

        //保存标签
        if (tags != null) {
            for (String tagstring : tags) {
                Tag tag = new Tag();
                tag.setName(tagstring);
                tagMapper.insert(tag);

                //保存标签与评价的关系表
                tagMapper.insertRelation(entry.getId(), tag.getId());
            }
        }
    }

    @Override
    public void delete(Long id) {
        strategyCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(StrategyComment entry) {
        strategyCommentMapper.updateByPrimaryKey(entry);
    }

    @Override
    public List<StrategyComment> listAll() {
        return strategyCommentMapper.selectAll();
    }

    @Override
    public PageInfo<StrategyComment> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        List<StrategyComment> list = strategyCommentMapper.selectForList(qo);
        return new PageInfo<StrategyComment>(list);
    }

}
