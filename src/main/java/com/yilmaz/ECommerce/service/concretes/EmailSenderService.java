package com.yilmaz.ECommerce.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void registerMail(String mail,String key) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("mahmutsami1360@gmail.com");
        mailMessage.setTo(mail);
        mailMessage.setSubject("Üyeliği Tamamla.");
        mailMessage.setText("Üyeliğinizi tamamlamak için linke tıklayınız: http://localhost:8080/api/users/reg/"+key);
        javaMailSender.send(mailMessage);


    }
}
