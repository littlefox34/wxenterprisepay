package com.xiaoqu6.wxenterprisepay.repositories;

import com.xiaoqu6.wxenterprisepay.models.PayOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PayOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayOrder record);

    int insertSelective(PayOrder record);

    PayOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayOrder record);

    int updateByPrimaryKey(PayOrder record);

    List<PayOrder> selectByOpenId(String openid);
}