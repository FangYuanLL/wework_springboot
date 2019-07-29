package com.example.wework.dao;

import com.example.wework.model.user_Customer;
import com.example.wework.model.user_CustomerKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface user_CustomerMapper {

    int deleteByPrimaryKey(user_CustomerKey key);

    int insert(user_Customer record);

    int insertSelective(user_Customer record);

    user_Customer selectByPrimaryKey(user_CustomerKey key);

    int updateByPrimaryKeySelective(user_Customer record);

    int updateByPrimaryKey(user_Customer record);

    user_Customer searchUser(@Param("account") String account ,@Param("password") String password);

    user_Customer SelectById(int id);

    List<user_Customer> selectAll();

    int deleteByID(int id);

    int updateEmpty(int id);

}