package com.example.wework.service.IMPL;

import com.example.wework.dao.collectionHouseMapper;
import com.example.wework.model.collectionHouse;
import com.example.wework.service.collectionHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class collectionHouseServiceIMPL implements collectionHouseService{

    @Autowired
    private collectionHouseMapper collectionHouseMapper;

    @Override
    public int insert(collectionHouse record) {

        return collectionHouseMapper.insert(record);
    }

    @Override
    public List<collectionHouse> displayCollection(int userCustomerid) {
        return collectionHouseMapper.displayCollection(userCustomerid);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return collectionHouseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<collectionHouse> selectByHouseID(int HouseID) {
        return collectionHouseMapper.selectByHouseID(HouseID);
    }
}
