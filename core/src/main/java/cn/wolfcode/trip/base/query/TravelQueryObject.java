package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelQueryObject extends QueryObject {
    //作者id
    private Long authorId;
    //文章所处类型
    private Integer state;
    //关键字
    private String keyword;
}
