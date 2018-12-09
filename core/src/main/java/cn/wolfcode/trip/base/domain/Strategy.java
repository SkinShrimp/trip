package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 大攻略
 */
@Setter
@Getter
public class Strategy extends BaseDomain {
    public static final int STATE_NORMAL = 0;//普通
    public static final int STATE_HOT = 1;//热门
    public static final int STATE_DISABLE = -1;//禁用
    //地区
    private Region place;
    //标题
    private String title;
    //副标题
    private String subTitle;
    //封面
    private String coverUrl;
    //状态
    private Integer state = STATE_NORMAL;

    public String getStateName() {
        if (this.state == STATE_NORMAL) {
            return "普通";
        } else if (this.state == STATE_HOT) {
            return "热门";
        } else {
            return "禁用";
        }
    }

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        if (place != null) {
            map.put("placeId", this.getPlace().getId());
        }
        map.put("coverUrl", this.getCoverUrl());
        map.put("title", this.getTitle());
        map.put("subTitle", this.getSubTitle());
        map.put("state", this.getState());
        return JSONUtils.toJSONString(map);
    }
}