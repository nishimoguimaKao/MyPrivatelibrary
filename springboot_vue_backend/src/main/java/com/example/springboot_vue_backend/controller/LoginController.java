package com.example.springboot_vue_backend.controller;

import com.example.springboot_vue_backend.pojo.User;
import com.example.springboot_vue_backend.result.Result;
import com.example.springboot_vue_backend.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserServiceImpl userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session){
        //对html标签进行转义，防止XSS攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

//        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
//            String message = "账号密码错误";
//            System.out.println("test");
//            return new Result(400);
//        } else {
//            return new Result(200);
//        }
        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            session.setAttribute("user",user);
            return new Result(200);
        }
    }
}
