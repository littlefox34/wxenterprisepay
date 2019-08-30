package com.xiaoqu6.wxenterprisepay.untils;

/**
 * @author: KOLO
 * @date:2018年12月19日 下午4:51:08
 * @version: 1.0
 */
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class AuthUtil {
    public static final String APPID = "wxd0717f239cc1f2c3";
    public static final String APPSECRET = "6c900d4083ad42a974a9bec74c337855";
    public static final String MCHID = "1497128652";
    public static final String PATERNERKEY = "6ae695eeff582fc416e11f34cf29867d";

    /**
     * @Title: getRequestIp
     * @Description: 获取用户的ip地址
     * @param:
     * @return:
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        return ip;
    }
}


