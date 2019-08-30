package com.xiaoqu6.wxenterprisepay.services.Impl;

import com.xiaoqu6.wxenterprisepay.models.User;
import com.xiaoqu6.wxenterprisepay.repositories.UserMapper;
import com.xiaoqu6.wxenterprisepay.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByOpenId(String openid) {
        return userMapper.selectByOpenId(openid);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
