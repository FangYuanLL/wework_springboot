package com.example.wework.service.IMPL;

import com.example.wework.dao.user_CustomerMapper;
import com.example.wework.model.user_Customer;
import com.example.wework.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceIMPL implements userService {

    @Autowired
    private user_CustomerMapper userCustomerMapper;

    @Override
    public user_Customer searchUser(String account, String password) {
        return  userCustomerMapper.searchUser(account,password);
    }

    @Override
    public int insert(user_Customer record) {
        return userCustomerMapper.insert(record);
    }

    @Override
    public user_Customer SelectById(int id) {
        return userCustomerMapper.SelectById(id);
    }

    @Override
    public int updateByPrimaryKey(user_Customer record) {
        return userCustomerMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<user_Customer> selectAll() {
        return userCustomerMapper.selectAll();
    }

    @Override
    public int deleteByID(int id) {
        return userCustomerMapper.deleteByID(id);
    }

    @Override
    public int updateEmpty(int id) {
        return userCustomerMapper.updateEmpty(id);
    }
}
