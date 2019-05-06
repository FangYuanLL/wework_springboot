package com.example.wework.dao;

import com.example.wework.model.house_Information;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface house_InformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(house_Information record);

    int insertSelective(house_Information record);

    house_Information selectByPrimaryKey(Integer id);

    /*start*/
    house_Information selectHouseID(@Param("uuid") String uuid );

    List<house_Information> selectHouseInformation(@Param("city") String city,@Param("area") String area,@Param("officetype") String officetype,@Param("status") String status );

    List<house_Information> getHouseInformationByLandlordID(int landlordid);

    List<house_Information> getUnCheckHouse( String status);

    int updateHouseStatus(int id);
    /*end*/

    int updateByPrimaryKeySelective(house_Information record);

    int updateByPrimaryKeyWithBLOBs(house_Information record);

    int updateByPrimaryKey(house_Information record);
}