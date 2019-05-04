package com.example.wework.controller;

import com.example.wework.model.house_Information;
import com.example.wework.model.user_Customer;
import com.example.wework.service.AdministratorService;
import com.example.wework.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/Administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administrator;

    @Autowired
    private userService userService;

    @ResponseBody
    @RequestMapping(value = "/getUnCheck")
    public Object getUnCheck(){
        Map map = new HashMap();
        String status = "0";
        List<house_Information> list = administrator.getUnCheckHouse(status);
        List<user_Customer> userList = new ArrayList<user_Customer>();
        for (int i=0;i<list.size();i++){
            house_Information house = null;
            house = list.get(i);
            user_Customer user = null;
            user = userService.SelectById(house.getLandlordid());
            userList.add(user);
        }
        if (list!=null){
            map.put("list",list);
            map.put("userList",userList);
            map.put("statuscode",1);
        }else {
            map.put("statuscode",-1);
        }

        return map;
    }
}
