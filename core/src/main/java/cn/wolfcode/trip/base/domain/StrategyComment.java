package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class StrategyComment extends BaseDomain {
    public static final int STATE_NORMAL = 0;//普通
    public static final int STATE_HOT = 1;//推荐
    public static final int STATE_DISABLE = -1;//禁用
    //评论人
    private User user;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //评论时间
    private Date createTime;
    //评论内容
    private String content;
    //评论图片
    private String imgUrls;
    //星星数量
    private Integer starNum;
    //大攻略
    private Strategy strategy;
    //状态
    private Integer state = STATE_NORMAL;
    //头条推荐时间
    private Date commendTime;

    public String[] getImages() {
        String[] split = this.imgUrls.split(";");
        return split;
    }
}