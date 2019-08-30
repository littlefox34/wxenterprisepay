package com.xiaoqu6.wxenterprisepay.controllers;

import com.xiaoqu6.wxenterprisepay.models.User;
import com.xiaoqu6.wxenterprisepay.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IUserService userService;

    /**
     * 获取所有用户
     *
     * @param model
     * @return
     */
    @GetMapping("/user")
    public String getUserAll(Model model) {
        List<User> userList = userService.selectAll();
        model.addAttribute("userlist", userList);
        return "Admin/User";
    }
    @GetMapping("/success")
    public String successPage(String msg, Integer stype, HashMap<String, String> map){
        map.put("message",msg);
        map.put("stype",stype.toString());
        return "Admin/Success";
    }

}

