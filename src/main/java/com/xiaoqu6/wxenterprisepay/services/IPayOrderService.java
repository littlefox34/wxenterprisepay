package com.xiaoqu6.wxenterprisepay.services;

import com.xiaoqu6.wxenterprisepay.models.PayOrder;

import java.util.List;

public interface IPayOrderService {
    int insert(PayOrder record);

    List<PayOrder> selectByOpenId(String openid);
}
