package com.xiaoqu6.wxenterprisepay.controllers;

import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.wxpay.sdk.WXPayUtil;
import com.xiaoqu6.wxenterprisepay.models.PayOrder;
import com.xiaoqu6.wxenterprisepay.models.User;
import com.xiaoqu6.wxenterprisepay.services.IPayOrderService;
import com.xiaoqu6.wxenterprisepay.services.IUserService;
import com.xiaoqu6.wxenterprisepay.untils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UpdateController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPayOrderService payOrderService;

    @Autowired
    private WxPayService wxPayService;

    private final static Logger logger = LoggerFactory.getLogger(UpdateController.class);


    @PostMapping("user/updateinfo")
    public boolean UpdateUser(User user){
        boolean result = userService.updateByPrimaryKeySelective(user)==1? true:false;
        return result;
    }

    /**
     * @Title: transfer
     * @Description: 企业转账到零钱
     * @param:
     * @return:
     */
    @RequestMapping("admin/pay")
    public Map<String, String> transfer(HttpServletRequest request, HttpServletResponse response) {
        String openid = request.getParameter("openid");
        String re_user_name = request.getParameter("truename"); // 收款用户姓名(非必须)
        Integer amount = Integer.parseInt(request.getParameter("amount"));
        EntPayRequest entPayRequest = new EntPayRequest();
        entPayRequest.setAppid(AuthUtil.APPID);
        entPayRequest.setMchId(AuthUtil.MCHID);
        entPayRequest.setPartnerTradeNo(WXPayUtil.generateNonceStr());
        entPayRequest.setOpenid(openid);
        entPayRequest.setCheckName("NO_CHECK");
        entPayRequest.setAmount(amount);
        entPayRequest.setDescription("这是一笔付款+");
        entPayRequest.setSpbillCreateIp(AuthUtil.getRequestIp(request));
        EntPayResult entPayResult = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            entPayResult = wxPayService.getEntPayService().entPay(entPayRequest);
            map.put("result", "success");
            PayOrder payOrder = new PayOrder();
            payOrder.setAmount(BigDecimal.valueOf(amount/100));
            payOrder.setOpenid(openid);
            payOrder.setTruename(re_user_name);
            payOrderService.insert(payOrder);
            return map;
        } catch (WxPayException e) {
            map.put("result", "error");
            map.put("message", e.getErrCodeDes());
            return map;
        }

    }
}
