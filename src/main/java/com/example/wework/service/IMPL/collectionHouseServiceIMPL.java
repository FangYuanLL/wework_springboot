package com.example.wework.service.IMPL;

import com.example.wework.dao.collectionHouseMapper;
import com.example.wework.model.collectionHouse;
import com.example.wework.service.collectionHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class collectionHouseServiceIMPL implements collectionHouseService{

    @Autowired
    private collectionHouseMapper collectionHouseMapper;

    @Override
    public int insert(collectionHouse record) {

        return collectionHouseMapper.insert(record);
    }
}
