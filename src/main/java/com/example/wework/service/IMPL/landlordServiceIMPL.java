package com.example.wework.service.IMPL;

import com.example.wework.dao.user_LandlordMapper;
import com.example.wework.model.user_Landlord;
import com.example.wework.service.landlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *landlord变为管理员的表了
 * 我觉得所有用户皆可以作为房主，写了一个房主委托功能即可
 */
@Service
public class landlordServiceIMPL implements landlordService {

    @Autowired
    private user_LandlordMapper user_landlordMapper;

    @Override
    public int insert(user_Landlord record) {
        return user_landlordMapper.insert(record);
    }

    @Override
    public user_Landlord searchLandlord(String account, String password) {
        return user_landlordMapper.searchLandlord(account,password);
    }
}
