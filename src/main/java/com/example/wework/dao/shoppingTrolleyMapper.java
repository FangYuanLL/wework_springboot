package com.example.wework.dao;

import com.example.wework.model.shoppingTrolley;

public interface shoppingTrolleyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(shoppingTrolley record);

    int insertSelective(shoppingTrolley record);

    shoppingTrolley selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(shoppingTrolley record);

    int updateByPrimaryKey(shoppingTrolley record);
}