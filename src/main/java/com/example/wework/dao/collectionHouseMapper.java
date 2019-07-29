package com.example.wework.dao;

import com.example.wework.model.collectionHouse;

import java.util.List;

public interface collectionHouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(collectionHouse record);

    int insertSelective(collectionHouse record);

    collectionHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(collectionHouse record);

    int updateByPrimaryKey(collectionHouse record);

    List<collectionHouse> displayCollection(int userCustomerid);

    List<collectionHouse> selectByHouseID(int houseID);
}