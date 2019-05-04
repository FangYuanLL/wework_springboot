package com.example.wework.service;

import com.example.wework.model.Business;

import java.util.List;

public interface BusinessService {
    int insert(Business record);

    List<Business> selectOrder(int customerId);

    List<Business> selectOrderByLandlord(int landlordid);

    Business selectByPrimaryKey(Integer id);

    Business selectOrderByMore(int customerId,String date,int houseId);

    int setStatus(int id);

    int DeleteBusinessByPrimaryKey(int id);
}
