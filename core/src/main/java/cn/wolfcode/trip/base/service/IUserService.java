package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IUserService {
    public abstract void save(User entry);

    /**
     * 更新数据
     *
     * @param entry
     */
    public abstract void update(User entry);

    /**
     * 用户注册
     *
     * @param user
     */
    public abstract void register(User user);

    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    public abstract User login(User user);

    /**
     * 分页查询
     *
     * @param qo
     */
    public abstract PageInfo<User> query(QueryObject qo);

}
