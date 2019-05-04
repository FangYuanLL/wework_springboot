package com.example.wework.dao;

import com.example.wework.model.collectionHouse;

public interface collectionHouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(collectionHouse record);

    int insertSelective(collectionHouse record);

    collectionHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(collectionHouse record);

    int updateByPrimaryKey(collectionHouse record);
}