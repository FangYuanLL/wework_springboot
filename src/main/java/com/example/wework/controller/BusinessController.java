package com.example.wework.controller;

import com.example.wework.dao.BusinessMapper;
import com.example.wework.model.Business;
import com.example.wework.model.house_Information;
import com.example.wework.model.user_Customer;
import com.example.wework.service.BusinessService;
import com.example.wework.service.HouseService;
import com.example.wework.service.userService;
import com.example.wework.utils.JWT;
import com.example.wework.utils.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/Business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private userService user;

    //我预定的办公室的订单记录
    @ResponseBody
    @RequestMapping(value = "/display")
    public Object selectOrder(HttpServletRequest request){
        Map map = new HashMap();

        /*获取登录时用户记录在cookie的数据*/
        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        /*------end-------*/

        List<Business> list = null;
        list = businessService.selectOrder(user.getId());
        //list = businessService.selectOrder(1);
        List housenamelist = new ArrayList();
        for (Business it:list){
            house_Information house = houseService.selectByPrimaryKey(it.getHouseId());
            housenamelist.add(house.getEmpty());
        }
        if (list!=null){
            map.put("list",list);
            map.put("housenamelist",housenamelist);
            map.put("status",1);
        }else {
            map.put("status",-1);
        }

        return map;
    }

    //交易订单
    @ResponseBody
    @RequestMapping(value = "/MyHouseOrder")
    public Object MyHouseOrder(HttpServletRequest request){
        Map map = new HashMap();

        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        List<Business> list = null;
        list = businessService.selectOrder(user.getId());
        //主要查询到关于登录用户委托房屋的订单
        List<Business> List = businessService.selectOrderByLandlord(user.getId());
        List<house_Information> housenamelist = new ArrayList<house_Information>();
        for (int i=0;i<List.size();i++){
            house_Information house = null;
            int id = List.get(i).getHouseId();
            house = houseService.selectByPrimaryKey(id);
            housenamelist.add(house);
        }
        if(List !=null){
            map.put("housenamelist",housenamelist);
            map.put("list",List);
            map.put("status",1);
        }else{
            map.put("status",-1);
        }
        return map;
    }

    //订单的详情
    @ResponseBody
    @RequestMapping(value = "/BusinessDetails")
    public Object BusinessDetails(HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        Business business = businessService.selectByPrimaryKey(id);
        user_Customer userCustomer = user.SelectById(business.getCustomerId());
        house_Information houseinformation = houseService.selectByPrimaryKey(business.getHouseId());
        Map map = new HashMap();

        if (userCustomer!=null && houseinformation!=null){
            map.put("status",1);
            map.put("business",business);
            map.put("userCustomer",userCustomer);
            map.put("houseinformation",houseinformation);
        }else {
            map.put("status",-1);
        }

        return map;
    }

    //修改订单的状态，是否支付定金的状态
    @ResponseBody
    @RequestMapping(value = "/SetBusinessStatus")
    public Object SetBusinessStatus(HttpServletRequest request){
        int  businessID = Integer.valueOf(request.getParameter("businessid"));
        int flag = businessService.setStatus(businessID);

        Business business = businessService.selectByPrimaryKey(businessID);
        house_Information house = houseService.selectByPrimaryKey(business.getHouseId());
        house.setRemainnumber(house.getRemainnumber()-business.getNumber());
        int tag = houseService.updateByPrimaryKeySelective(house);
        Map map = new HashMap();
        if (flag > 0 ){
            map.put("status",1);
        }else {
            map.put("status",-1);
        }
        return map;
    }

    //确认订单完成，设置房屋的remainnumber
    @ResponseBody
    @RequestMapping(value = "/confirmMyOrderById")
    public Object confirmMyOrderId(HttpServletRequest request){
        int  businessID = Integer.valueOf(request.getParameter("businessid"));
        Map map = new HashMap();
        Business business = businessService.selectByPrimaryKey(businessID);
        int houseid = business.getHouseId();
        int number = business.getNumber();
        house_Information house = houseService.selectByPrimaryKey(houseid);
        int remainnumber = house.getRemainnumber()+number;
        int flag = houseService.updateHouseRemainnumber(houseid,remainnumber);
        int businessflag = businessService.confirmMyOrderId(businessID);

        if (flag>0 && businessflag>0){
            map.put("status",1);
        }else {
            map.put("status",-1);
        }


        return map;
    }

    //删除对应的订单
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Object DeleteBusiness(HttpServletRequest request){
        Map map = new HashMap();

        int businessid = Integer.valueOf(request.getParameter("BusinessID"));

        int flag = businessService.DeleteBusinessByPrimaryKey(businessid);

        if (flag > 0){
            map.put("status",1);
        }else {
            map.put("status",-1);
        }

        return map;
    }

}
