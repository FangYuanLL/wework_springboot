package com.example.wework.service;

import com.example.wework.model.house_Information;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseService {

    int insert(house_Information record);

    house_Information selectHouseID(String uuid);

    List<house_Information> selectHouseInformation(String city, String area, String officetype ,String status);

    house_Information selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(house_Information record);

    List<house_Information> getHouseInformationByLandlordID(int landlordid);

    int deleteByPrimaryKey(Integer id);

}
