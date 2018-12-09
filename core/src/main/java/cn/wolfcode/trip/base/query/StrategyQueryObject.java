package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StrategyQueryObject extends QueryObject {
    //关键字
    private String keyword;
    //当前攻略的状态
    private Integer state;
    //地区Id
    private Integer regionId;
}
