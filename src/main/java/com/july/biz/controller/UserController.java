package com.july.biz.controller;

import com.july.biz.entity.UserEntity;
import com.july.biz.service.UserService;
import com.july.common.CommonResponse;
import com.july.common.Constant;
import com.july.common.PageData;
import com.july.common.annotion.RequireLogin;
import com.july.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public CommonResponse login(HttpServletRequest request, HttpServletResponse response) {
        String token = UUID.randomUUID().toString();
        // 从后台取userID
        int userId = 1;
        // 存储token
        redisTemplate.opsForValue().set("token" + userId, token);
        CookieUtils.setNewCookie(Constant.COOKIE_TOKEN_NAME, token, response);
        return CommonResponse.success();
    }

    @RequireLogin
    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    public CommonResponse getUserList() {
        PageData pageData = new PageData();
        List<UserEntity> list = userService.getUserList();
        pageData.setData(list);
        pageData.setTotal(list.size());
        return CommonResponse.successWithData(pageData);
    }


}
