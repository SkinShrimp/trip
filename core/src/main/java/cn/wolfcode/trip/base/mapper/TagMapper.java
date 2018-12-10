package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.QueryObject;import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    Tag selectByPrimaryKey(Long id);

    List<Tag> selectAll();

    int updateByPrimaryKey(Tag record);

    List<Tag> selectTop(QueryObject qo);

    void insertRelation(@Param("commentId")Long commentId, @Param("tagId")Long tagId);
}