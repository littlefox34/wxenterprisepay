package com.xiaoqu6.wxenterprisepay.controllers;

import com.xiaoqu6.wxenterprisepay.untils.AesException;
import com.xiaoqu6.wxenterprisepay.untils.WXPublicUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WechatQRCodeController {

    @GetMapping(value="/wxpublic/verifywxtoken",produces = "text/plain;charset=UTF-8")
    public String verifyWXToken(HttpServletRequest request) throws AesException {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (WXPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
            return echostr;
        }
        return null;
    }
}
