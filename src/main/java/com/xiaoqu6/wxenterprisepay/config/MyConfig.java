package com.xiaoqu6.wxenterprisepay.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hsw
 */
@Configuration
public class MyConfig {

    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId("wxd0717f239cc1f2c3");
        payConfig.setMchId("1497128652");
        payConfig.setMchKey("6ae695eeff582fc416e11f34cf29867d");
        payConfig.setNotifyUrl("http://xcx.xiaoqu7.com/admin/");
        payConfig.setKeyPath("classpath:apiclient_cert.p12");
        payConfig.setTradeType("JSAPI");
        payConfig.setSignType("MD5");
        return payConfig;
    }


    @Bean
    public WxPayService wxPayService(WxPayConfig payConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
