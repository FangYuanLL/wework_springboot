package com.example.wework.service;

import com.example.wework.model.Image;

import java.util.List;

public interface ImageService {

    int insert(Image record);

    List<Image> selectByHouseId(int houseid);
}
