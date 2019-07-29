package com.example.wework.service;

import com.example.wework.model.house_Information;

public interface MailService {
    int SendMail(String SendTo, house_Information house);
}
