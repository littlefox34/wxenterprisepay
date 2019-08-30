package com.xiaoqu6.wxenterprisepay.controllers;

import com.xiaoqu6.wxenterprisepay.models.PayOrder;
import com.xiaoqu6.wxenterprisepay.models.User;
import com.xiaoqu6.wxenterprisepay.services.IPayOrderService;
import com.xiaoqu6.wxenterprisepay.services.IUserService;
import com.xiaoqu6.wxenterprisepay.services.Impl.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weixin4j.WeixinException;
import org.weixin4j.spring.WeixinTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPayOrderService payOrderService;

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/index")
    public String Index(HttpServletRequest request, Model model) {
        Object oauth_openid = request.getSession().getAttribute("openid");
        int userid = 0;
        User user = new User();
        if(oauth_openid != null)
        {
            user = userService.selectByOpenId(oauth_openid.toString());
            if( user == null ) {
                user = new User();
                user.setOpenid(oauth_openid.toString());
                userid = userService.insertSelective(user);
                user = userService.selectByPrimaryKey(userid);
            }
        }
        List<PayOrder> payOrderList = payOrderService.selectByOpenId(oauth_openid.toString());
        model.addAttribute("user", user);

        return "Home/Index";
    }

    @GetMapping("/auth")
    public void getAuth(HttpServletRequest request, HttpServletResponse response) throws IOException, WeixinException {
        validateOAuthOpenId(request,response,"http://xcx.xiaoqu7.com/home/auth");
    }

}
