package com.example.wework.utils;

import java.util.UUID;


//用UUID类生成唯一的id值
public class CreateUUID {
    public String getUUID(){
        String id =  UUID.randomUUID().toString();

        return id;
    }

}
