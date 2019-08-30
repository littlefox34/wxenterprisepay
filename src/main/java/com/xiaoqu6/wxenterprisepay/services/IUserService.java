package com.xiaoqu6.wxenterprisepay.services;

import com.xiaoqu6.wxenterprisepay.models.User;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public interface IUserService {

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByOpenId(String openid);

    int updateByPrimaryKey(User record);

    int updateByPrimaryKeySelective(User record);

    List<User> selectAll();
}
