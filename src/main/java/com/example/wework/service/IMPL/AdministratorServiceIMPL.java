package com.example.wework.service.IMPL;

import com.example.wework.dao.house_InformationMapper;
import com.example.wework.model.house_Information;
import com.example.wework.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceIMPL implements AdministratorService {

    @Autowired
    private house_InformationMapper house;

    @Override
    public List<house_Information> getUnCheckHouse(String status) {
        return house.getUnCheckHouse(status);
    }
}
