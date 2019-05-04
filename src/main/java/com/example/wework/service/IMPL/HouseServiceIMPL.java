package com.example.wework.service.IMPL;

import com.example.wework.dao.house_InformationMapper;
import com.example.wework.model.house_Information;
import com.example.wework.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceIMPL implements HouseService {

    @Autowired
    private house_InformationMapper house;


    @Override
    public int insert(house_Information record) {
        return  house.insert(record);
    }

    @Override
    public house_Information selectHouseID(String uuid) {
        return house.selectHouseID(uuid);
    }

    @Override
    public List<house_Information> selectHouseInformation(String city, String area, String officetype ,String status) {
        return house.selectHouseInformation(city,area,officetype,status);
    }

    @Override
    public house_Information selectByPrimaryKey(Integer id) {
        return house.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(house_Information record) {
        return house.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<house_Information> getHouseInformationByLandlordID(int landlordid) {
        return  house.getHouseInformationByLandlordID(landlordid);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return house.deleteByPrimaryKey(id);
    }
}
