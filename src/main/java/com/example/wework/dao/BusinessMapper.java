package com.example.wework.dao;

import com.example.wework.model.Business;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    List<Business> selectOrder(int customerId);

    List<Business> selectOrderByLandlord(int landlordid);

    Business selectOrderByMore(@Param("customerId") int customerId,@Param("date") String date,@Param("houseid") int houseid);

    //订单是否支付定金的状态
    int setStatus(@Param("businessid") int id);
    //订单是否完成的状态
    int confirmMyOrderId(int id);

}