package com.example.wework.service.IMPL;

import com.example.wework.dao.BusinessMapper;
import com.example.wework.model.Business;
import com.example.wework.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceIMPL implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public int insert(Business record) {
        return businessMapper.insert(record);
    }

    @Override
    public List<Business> selectOrder(int customerId) {
        return businessMapper.selectOrder(customerId);
    }

    @Override
    public List<Business> selectOrderByLandlord(int landlordid) {
        return businessMapper.selectOrderByLandlord(landlordid);
    }

    @Override
    public Business selectByPrimaryKey(Integer id) {
        return businessMapper.selectByPrimaryKey(id);
    }

    @Override
    public Business selectOrderByMore(int customerId, String date, int houseId) {
        String starttime = "2019-04-06 00:00";
        String endtime = "2019-04-20 00:00";
        return businessMapper.selectOrderByMore(customerId,date,houseId);
    }

    @Override
    public int setStatus(int id) {
        return businessMapper.setStatus(id);
    }

    @Override
    public int DeleteBusinessByPrimaryKey(int id) {
        return businessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int confirmMyOrderId(int id) {
        return businessMapper.confirmMyOrderId(id);
    }
}
