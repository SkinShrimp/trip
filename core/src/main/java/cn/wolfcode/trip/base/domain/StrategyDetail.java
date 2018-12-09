package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    public String getStateName() {
        switch (this.state) {
            case STATE_NORMAL:
                return "草稿";
            case STATE_RELEASE:
                return "发布";
            default:
                return "禁用";
        }

    }
}