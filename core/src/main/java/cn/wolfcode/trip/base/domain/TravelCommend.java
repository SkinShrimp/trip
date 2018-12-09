package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class TravelCommend extends BaseDomain {

    public static final int COMMEND_TYPE = 3;//攻略推荐
    public static final int WEEK_TYPE = 2;//每周推荐
    public static final int MONTH_TYPE = 1;//每月推荐

    private Travel travel;//游记

    private String title;//主标题

    private String subTitle;//副标题

    private String coverUrl;//封面

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date schedule;//推荐时间安排

    private Integer type = MONTH_TYPE;//类型

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("title", this.title);
        map.put("subTitle", this.subTitle);
        map.put("coverUrl", this.coverUrl);
        map.put("schedule", this.schedule);
        map.put("type", this.type);
        return JSONUtils.toJSONString(map);
    }

    public String getTypes() {
        switch (this.type) {
            case MONTH_TYPE:
                return "每月推荐";
            case WEEK_TYPE:
                return "每周推荐";
            default:
                return "攻略推荐";
        }
    }
}