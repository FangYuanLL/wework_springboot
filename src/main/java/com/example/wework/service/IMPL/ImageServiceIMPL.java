package com.example.wework.service.IMPL;

import com.example.wework.dao.ImageMapper;
import com.example.wework.model.Image;
import com.example.wework.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceIMPL implements ImageService {

    @Autowired
    private ImageMapper image;

    @Override
    public int insert(Image record) {
        return image.insert(record);
    }

    @Override
    public List<Image> selectByHouseId(int houseid) {
        return image.selectByHouseId(houseid);
    }
}
