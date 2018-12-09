package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 攻略分类
 */
@Setter
@Getter
public class StrategyCatalog extends BaseDomain {
    //名称
    private String name;
    //所属攻略
    private Strategy strategy;
    //序号
    private Integer sequence;
    //状态
    private Boolean state;

    private List<StrategyDetail> strategyDetails;

    public String getStateName() {
        if (state != null) {
            if (state) {
                return "启用";
            }
        }
        return "禁止";
    }

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("name", this.getName());
        if (strategy != null) {
            map.put("strategyId", this.getStrategy().getId());
            map.put("title", this.getStrategy().getTitle());
        }
        map.put("sequence", this.getSequence());
        map.put("state", this.getState());
        return JSONUtils.toJSONString(map);
    }
}