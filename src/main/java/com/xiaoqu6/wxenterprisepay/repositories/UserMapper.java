package com.xiaoqu6.wxenterprisepay.repositories;

import com.xiaoqu6.wxenterprisepay.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByOpenId(String openid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();
}