package com.example.wework.service;

import com.example.wework.model.collectionHouse;

import java.util.List;

public interface collectionHouseService {
    int insert(collectionHouse record);

    List<collectionHouse> displayCollection(int userCustomerid);

    int deleteByPrimaryKey(Integer id);

    List<collectionHouse> selectByHouseID(int HouseID);
}
