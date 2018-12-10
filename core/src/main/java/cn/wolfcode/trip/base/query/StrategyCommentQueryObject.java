package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StrategyCommentQueryObject extends QueryObject {
    //状态
    private Long state;
    //大攻略
    private Long strategyId;
}
