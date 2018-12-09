package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelCommendQueryObject extends QueryObject {
    //关键字
    private String keyword;
    //文章所处推荐类型
    private Integer type;
}
