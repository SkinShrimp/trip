package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User entry) {

    }

    @Override
    public void update(User entry) {
        userMapper.updateByPrimaryKey(entry);
    }

    @Override
    public void register(User user) {
        if (!StringUtils.hasLength(user.getEmail())) {
            throw new RuntimeException("账号为空！！！");
        }
        //1、检查邮箱原来是否被注册过
        User entry = userMapper.selectByEmailAndPassword(user.getEmail(), null);
        if (entry != null) {
            throw new RuntimeException("亲！该用户已被注册!");
        }

        //2、保存到数据库
        userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        if (!StringUtils.hasLength(user.getEmail())) {
            throw new RuntimeException("账号为空！！！");
        }
        //1、检查账号密码是否正确
        User entry = userMapper.selectByEmailAndPassword(user.getEmail(), user.getPassword());
        if (entry == null) {
            throw new RuntimeException("亲！账号或者密码错误!");
        }
        //把user放在session中
        UserContext.setCurrentUser(entry);
        return entry;
    }

    @Override
    public PageInfo<User> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<User> list = userMapper.selectForList(qo);
        return new PageInfo<User>(list);
    }
}
