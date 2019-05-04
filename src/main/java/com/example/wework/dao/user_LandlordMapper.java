package com.example.wework.dao;

import com.example.wework.model.user_Landlord;
import com.example.wework.model.user_LandlordKey;
import org.apache.ibatis.annotations.Param;

public interface user_LandlordMapper {
    int deleteByPrimaryKey(user_LandlordKey key);

    int insert(user_Landlord record);

    int insertSelective(user_Landlord record);

    user_Landlord selectByPrimaryKey(user_LandlordKey key);

    int updateByPrimaryKeySelective(user_Landlord record);

    int updateByPrimaryKey(user_Landlord record);

    user_Landlord searchLandlord(@Param("account") String account,@Param("password") String password);
}