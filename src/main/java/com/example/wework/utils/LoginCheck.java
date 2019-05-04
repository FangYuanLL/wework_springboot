package com.example.wework.utils;


import com.example.wework.model.user_Customer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class LoginCheck {

    //验证管理员登录状态
    public static user_Customer proveMe(HttpServletRequest request){
        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        //遍历cookie
        for(int i=0; cookies!=null && i<cookies.length; i++){
            if("userCustomer".equals(cookies[i].getName())){
                String token = cookies[i].getValue();
                //解密token得到用户信息
                user_Customer user = JWT.unsign(token, user_Customer.class);
                //判断是否拿到过期的验证
                if(user != null) {
                    return user;
                }

            }
        }
        return null;
    }
    //验证登录的是管理员还是普通用户
    public static String checkCurrent(HttpServletRequest request){
        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        //遍历cookie
        for(int i=0; cookies!=null && i<cookies.length; i++){
            if("current".equals(cookies[i].getName())){
                String token = cookies[i].getValue();

                if(token != null) {
                    return token;
                }

            }
        }
        return null;
    }

    //判断是否登录
    public static String WhetherLogin(HttpServletRequest request){
        String LoginStatus = checkCurrent(request);
        if (LoginStatus!=null){
            return LoginStatus;
        }else {
            return null;
        }
    }

}
