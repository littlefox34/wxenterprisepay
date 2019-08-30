package com.xiaoqu6.wxenterprisepay.services.Impl;

import com.xiaoqu6.wxenterprisepay.models.PayOrder;
import com.xiaoqu6.wxenterprisepay.repositories.PayOrderMapper;
import com.xiaoqu6.wxenterprisepay.services.IPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayOrderService implements IPayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Override
    public int insert(PayOrder record) {
        return payOrderMapper.insert(record);
    }

    @Override
    public List<PayOrder> selectByOpenId(String openid) {
        return payOrderMapper.selectByOpenId(openid);
    }
}
