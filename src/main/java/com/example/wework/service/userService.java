package com.example.wework.service;

import com.example.wework.model.user_Customer;

public interface userService {

        user_Customer searchUser(String account, String password);

        int insert(user_Customer record);

        user_Customer SelectById(int id);

        int updateByPrimaryKey(user_Customer record);
}
