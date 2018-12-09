package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User extends BaseDomain {
    public static final int MAN = 1;//男
    public static final int WOMAN = 0;//女
    public static final int SECRET = -1;//保密

    public String getGenders() {
        switch (this.gender) {
            case 1:
                return "男";
            case 0:
                return "女";
            default:
                return "保密";
        }
    }

    private String email;//邮箱

    private String nickName;//昵称

    private String password;//密码

    private String place;//所属地区

    private String headimgurl;//头像

    private Integer gender = SECRET;//性别

    private String coverimgurl;//封面

    private String sign;//签名

}