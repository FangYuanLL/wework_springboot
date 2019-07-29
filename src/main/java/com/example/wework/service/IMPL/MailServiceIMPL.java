package com.example.wework.service.IMPL;

import com.example.wework.model.house_Information;
import com.example.wework.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceIMPL implements MailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public int SendMail(String SendTo, house_Information house) {
        SimpleMailMessage message = new SimpleMailMessage();
        String sender = SendTo;
        message.setFrom("1051788723@qq.com");
        message.setTo(sender);
        message.setSubject("主题:办公室价格下调");
        String Text ="用户你好！您关注的共享办公室"+house.getEmpty()+"价格下调至"+house.getPrice()+",敬请关注！";
        message.setText(Text);
        javaMailSender.send(message);

        return 1;
    }
}
