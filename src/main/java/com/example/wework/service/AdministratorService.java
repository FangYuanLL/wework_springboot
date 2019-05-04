package com.example.wework.service;

import com.example.wework.model.house_Information;

import java.util.List;

public interface AdministratorService {

    List<house_Information> getUnCheckHouse(String status);
}
