package com.example.wework.controller;

import com.example.wework.model.user_Customer;
import com.example.wework.service.IMPL.userServiceIMPL;
import com.example.wework.service.userService;
import com.example.wework.utils.JWT;
import com.example.wework.utils.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *用户user_Customer进行的操作
 */
@Controller
@RequestMapping(value = "/User")
public class UserController {

    @Autowired
    private userService userService;
    /*
     * userLogin()方法用于用户登录
     * request用于获取前台传过来的account账号，password密码
     * map用于存储状态码status
     * status:-1 非正常状态 1 正常状态
     * cookie 用于存储用户信息，以便于交易时获取用户id
     * 利用jwt将登录的user用户变成加密的字符串存储
     */
    @ResponseBody
    @RequestMapping(value = "/Login")
    public Object userLogin(HttpServletRequest request, HttpServletResponse response){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
       // System.out.println(account+"\n");
        //System.out.println(password+"\n");
        Map map = new HashMap();
        user_Customer user_customer = userService.searchUser(account,password);//调用service方法，查找对应account和password的记录
        String token = JWT.sign(user_customer,1L*48L*3600L*1000L);//将用户信息用JWT加密并变成字符串
        if((user_customer!=null) && (user_customer.getEmpty().equals("1")==true)){//判断是否有这个用户，判断用户是否是被删除的
            map.put("status",1);
            /*创建cookie存储用户信息并存入response中*/
            Cookie cookie = new Cookie("userCustomer",token);
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);

            /*创建cookie存储用户类型并存入response中*/
            Cookie CurrentCookie = new Cookie("current","1");
            CurrentCookie.setPath("/");
            CurrentCookie.setMaxAge(-1);
            response.addCookie(CurrentCookie);
        }else{
            map.put("status",-1);
        }
        return map;
    }

    /*
     *用户注销功能
     * status状态码 0:正常退出
     * cookie的名字相同，抵消前一个同名的cookie
     * 删除一个cookie
     */
    @ResponseBody
    @RequestMapping(value = "/Out")
    public Object userOut(HttpServletResponse response){
        Map map = new HashMap();
        Cookie killMyCookie = new Cookie("userCustomer",null);
        killMyCookie.setMaxAge(0);
        killMyCookie.setPath("/");
        response.addCookie(killMyCookie);


        Cookie killCurrentCookie = new Cookie("current",null);
        killCurrentCookie.setMaxAge(0);
        killCurrentCookie.setPath("/");
        response.addCookie(killCurrentCookie);
        map.put("status",1);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public Object userRegister(HttpServletRequest request,HttpServletResponse response){
        Map map = new HashMap();
        /*
         *可以试试直接放入一个类在方法里面，试试会不会自动封装。
         */
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phonenumber = request.getParameter("phonenumber");
        String idcard = request.getParameter("email");//idcard,现在是用作邮箱字段
        user_Customer userCustomer = new user_Customer();
        userCustomer.setAccount(account);
        userCustomer.setPassword(password);
        userCustomer.setName(name);
        userCustomer.setPhonenumber(phonenumber);
        userCustomer.setIdcard(idcard);//电话号码字段
        userCustomer.setEmpty("1");
        //有个邮箱没写进去？？？？？？？页面没写进去
        //flag存储插入操作的返回值
        int flag = userService.insert(userCustomer);

        user_Customer newUser = userService.searchUser(account,password);

        System.out.println("newUser:"+newUser.getId()+""+newUser.getName());
        if (newUser !=null){
            System.out.println("register:flag "+flag);
            map.put("status",1);
            String token = JWT.sign(newUser,1L*48L*3600L*1000L);
            Cookie cookie = new Cookie("userCustomer",token);
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            Cookie CurrentCookie = new Cookie("current","1");
            CurrentCookie.setPath("/");
            CurrentCookie.setMaxAge(-1);
            response.addCookie(CurrentCookie);
            System.out.println("register:addCookie-end");
        }else{
            map.put("status",-1);
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value="/SelectById")
    public Object SelectById(HttpServletRequest request){
        Map map = new HashMap();
        /*获取登录用户记录在cookie的数据*/
        /*String cookieValue=null;
        Cookie[]cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("userCustomer"));
                cookieValue = cookie.getValue();
            }
        }
        user_Customer user = new user_Customer();
        user = JWT.unsign(cookieValue,user_Customer.class);*/

        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        /*------end------*/
        if (user !=null){
            map.put("user",user);
            map.put("status",1);
        }else {
            map.put("status",-1);
            System.out.println("cookie里面的user未获取！！！！！有误！！！！！");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value="/updateUser")
    public Object UpdateUser(HttpServletRequest request){
        Map map = new HashMap();
        String password = request.getParameter("password");
        String phonenumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");

        user_Customer user = null;
        user = LoginCheck.proveMe(request);

        if (password.equals("") == false){
            user.setPassword(password);
        }
        if (phonenumber.equals("") == false){
            user.setPhonenumber(phonenumber);
        }
        if (email.equals("") == false){
            //System.out.println("email");
            user.setIdcard(email);
        }
        //System.out.println("email:"+user.getIdcard());
        int flag = userService.updateByPrimaryKey(user);

        if (flag>0){
            map.put("status",1);
        }else {
            map.put("status",-1);
        }

        return map;
    }
    @ResponseBody
    @RequestMapping(value="/CurrentUser")
    public Object CurrentUser(HttpServletRequest request){
        Map map = new HashMap();


        String userType = LoginCheck.checkCurrent(request);

        if (userType == null){
            System.out.println("currenUser:未登录!");
            map.put("status",-3);
        }else{
            if(userType.equals("2") == true){
                System.out.println("currenUser:administrator");
                map.put("status",-1);
            }else{
                System.out.println("currenUser:customer");
                map.put("status",1);
            }
        }

        return map;
    }
    //userManage.html页面用户信息的显示
    @ResponseBody
    @RequestMapping(value="/DisplayUser")
    public Object DisplayUser(HttpServletRequest request){
        Map map = new HashMap();
        user_Customer user = LoginCheck.proveMe(request);
        user_Customer newUser = userService.SelectById(user.getId());
        if (user!=null){
            map.put("status",1);
            map.put("user",newUser);
        }else{
            map.put("status",-1);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value="/selectAllUser")
    public Object SelectAllUser(){
        Map map = new HashMap();
        List<user_Customer> list = null;
        list = userService.selectAll();
        if (list!=null){
            map.put("userlist",list);
            map.put("status",1);
        }else{
            map.put("status",-1);
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value="/deleteByID")
    public Object deleteByID(HttpServletRequest request){
        Map map = new HashMap();
        String userid = request.getParameter("id");
        int id = Integer.valueOf(userid);
        int flag = userService.updateEmpty(id);
        if (flag > 0){
            map.put("status",1);
        }else{
            map.put("status",-1);
        }

        return map;
    }
}
