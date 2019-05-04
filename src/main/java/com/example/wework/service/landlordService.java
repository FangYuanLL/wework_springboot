package com.example.wework.service;

import com.example.wework.model.user_Landlord;
/*
 *landlord变为管理员的表了
 * 我觉得所有用户皆可以作为房主，写了一个房主委托功能即可
 */
public interface landlordService {

    int insert(user_Landlord record);

    user_Landlord searchLandlord(String account,String password);
}
