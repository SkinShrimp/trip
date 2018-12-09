package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Region extends BaseDomain {
    private static final int STATE_NORMAL = 0;//普通状态
    private static final int STATE_HOT = 1;//热门状态

    private String name;//地域名称

    private Region parent;//上级地区

    private Integer state = STATE_NORMAL;//状态

    public Map<String, Object> toTreeMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("text", this.getName());
        map.put("lazyLoad", true);
        if (this.state == STATE_HOT) {
            map.put("tags", new String[]{"推荐"});
        }
        return map;
    }

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("name", this.getName());
        map.put("state", this.getState());
        if (parent != null) {
            map.put("parentId", this.parent.getId());
            map.put("parentName", this.parent.getName());
        }
        return JSONUtils.toJSONString(map);
    }
}