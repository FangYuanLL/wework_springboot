package com.example.wework.controller;


import com.example.wework.model.user_Landlord;
import com.example.wework.service.landlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
 *landlord变成管理员登录的表，因为写错表了，最后改变了表
 */
@Controller
@RequestMapping(value="/Landlord")
public class LandlordController {

    @Autowired
    private landlordService landlordService;

    /*
     *管理员登录功能
     * @map用于存储状态码
     * @ResponseBody将map变为json数据返回前台
     */
    @ResponseBody
    @RequestMapping(value="/login")
    public Object landlordLogin(HttpServletRequest request,HttpServletResponse response){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        user_Landlord landlord = landlordService.searchLandlord(account,password);


        Cookie CurrentCookie = new Cookie("current","2");
        CurrentCookie.setPath("/");
        CurrentCookie.setMaxAge(-1);
        response.addCookie(CurrentCookie);

        Map map = new HashMap();
        if(landlord != null){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }

    /*
     *管理员注册功能
     * @map用于存储状态码
     * @ResponseBody将map变为json数据返回前台
     */
    @ResponseBody
    @RequestMapping(value="/register")
    public Object landlordRegister(user_Landlord landlord,HttpServletResponse response){
        int flag = landlordService.insert(landlord);

        Cookie CurrentCookie = new Cookie("current","2");
        CurrentCookie.setPath("/");
        CurrentCookie.setMaxAge(-1);
        response.addCookie(CurrentCookie);


        Map map = new HashMap();
        if(flag>0){
            map.put("status",1);
        }else {
            map.put("status",0);
        }
        return map;
    }
}
