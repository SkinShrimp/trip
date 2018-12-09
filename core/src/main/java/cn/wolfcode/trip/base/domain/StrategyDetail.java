package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class StrategyDetail extends BaseDomain {
    public static final int STATE_NORMAL = 0;//草稿
    public static final int STATE_RELEASE = 1;//发布
    public static final int STATE_DISABLE = -1;//禁用
    //标题
    private String title;
    //创建时间
    private Date createTime;
    //发布时间
    private Date releaseTime;
    //序号
    private Integer sequence;
    //所属分类
    private StrategyCatalog catalog;
    //封面
    private String coverUrl;
    //状态
    private Integer state;

    private StrategyContent strategyContent;//攻略文章的具体内容

    public String getStateName() {
        if (state != null) {
            switch (this.state) {
                case STATE_NORMAL:
                    return "草稿";
                case STATE_RELEASE:
                    return "发布";
                default:
                    return "禁用";
            }
        }
        return "禁用";
    }

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("title", this.title);
        map.put("coverUrl", this.coverUrl);
        if (catalog != null) {
            map.put("catalogId", catalog.getId());
            if (catalog.getStrategy() != null) {
                map.put("strategyId", catalog.getStrategy().getId());
            }
        }
        map.put("state", this.state);
        map.put("sequence", this.sequence);
        return JSONUtils.toJSONString(map);
    }
}