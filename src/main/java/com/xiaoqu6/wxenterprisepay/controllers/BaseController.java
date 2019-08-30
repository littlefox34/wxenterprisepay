package com.xiaoqu6.wxenterprisepay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.weixin4j.Configuration;
import org.weixin4j.WeixinException;
import org.weixin4j.component.SnsComponent;
import org.weixin4j.spring.WeixinTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {
    @Autowired
    private WeixinTemplate weixinTemplate;

    /**
     * 校验网页授权并获取openid
     *
     * @param request 请求对象
     * @param response 输出对象
     * @param returnUrl 网页授权后跳转回链接
     * @return 是否已获取openid
     * @throws IOException
     */
    public boolean validateOAuthOpenId(HttpServletRequest request, HttpServletResponse response, String returnUrl) throws IOException, WeixinException {
        //从session中获取openid
        Object oauth_openid = request.getSession().getAttribute("openid");
        //第一次访问,判断是否存在openid，不存在则说明没有进行授权访问，进行授权访问
        if (oauth_openid == null) {
            //获取Sns组件
            SnsComponent snsComponent = weixinTemplate.sns();
            //获取code，换取openid
            String code = request.getParameter("code");
            //如果没有获取到，则说明是直接访问页面链接，进行匿名获取
            if (code == null || code.equals("")) {
                //生成静默授权获取openid跳转链接
                String url = snsComponent.getOAuth2CodeBaseUrl(returnUrl);
                //跳转到微信授权页面
                response.sendRedirect(url);
                return false;
            } else {
                //获取授权得到的openid
                String openid = snsComponent.getOpenId(code);
                //设置当前用户
                request.getSession().setAttribute("openid", openid);
                //重定向到URL
                response.sendRedirect("/home/index");
                return false;
            }
        }
        return true;
    }

}
