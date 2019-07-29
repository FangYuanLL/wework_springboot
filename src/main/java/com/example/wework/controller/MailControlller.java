package com.example.wework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/mail")
public class MailControlller {

    /*@Autowired
    private JavaMailSender javaMailSender;

    @ResponseBody
    @RequestMapping(value="/send")
    public void SendEmail(HttpServletRequest request){
        SimpleMailMessage message = new SimpleMailMessage();

        String sender = "1720957640@qq.com";
        message.setFrom("1051788723@qq.com");
        message.setTo(sender); //自己给自己发送邮件
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        javaMailSender.send(message);

    }*/
}
