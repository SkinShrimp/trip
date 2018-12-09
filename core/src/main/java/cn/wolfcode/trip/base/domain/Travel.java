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
public class Travel extends BaseDomain {
    public static final int STATE_DRAFT = 0;//草稿
    public static final int STATE_VERIFY = 1;//待审核
    public static final int STATE_RELEASE = 2;//已发布
    public static final int STATE_BACK = 3;//被退回

    private String title;//标题

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date travelTime;//旅游时间

    private String perExpends;//人均消费

    private Integer days;//旅游天数

    private Long person;//和谁旅游

    private User author;//作者

    private Date createTime;//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;//发布时间

    private Boolean isPublic;//是否公开

    private Region place;//旅游地区

    private String coverUrl;//封面

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastupdateTime;//最后更新时间

    private Integer state = STATE_DRAFT;//文章状态(默认草稿)

    private TravelContent travelContent;

    public String getCurrentState() {
        switch (this.state) {
            case STATE_DRAFT:
                return "草稿";
            case STATE_VERIFY:
                return "待审核";
            case STATE_RELEASE:
                return "已发布";
            default:
                return "被退回";
        }
    }

    public String getPersons() {
        switch (this.person.intValue()) {
            case 1:
                return "一个人旅行";
            case 2:
                return "和父母";
            case 3:
                return "和朋友";
            case 4:
                return "和同事";
            case 5:
                return "和爱人";
            default:
                return "被退回";
        }
    }

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("title", this.title);
        map.put("coverUrl", this.coverUrl);
        return JSONUtils.toJSONString(map);
    }
}