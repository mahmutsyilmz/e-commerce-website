package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.model.concretes.User;
import com.yilmaz.ECommerce.repository.abstracts.UserRepository;
import com.yilmaz.ECommerce.service.concretes.EmailSenderService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserManager {
    private EmailSenderService emailSenderService;
    private UserRepository userRepository;

    public UserManager(EmailSenderService emailSenderService, UserRepository userRepository) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
    }

    public User register(User user) {
        // Kullanıcının e-posta doğrulama anahtarını oluşturun
        String key = UUID.randomUUID().toString();
        user.setKey(key);
        // Kullanıcıyı veritabanına kaydedin
        User savedUser = userRepository.save(user);

        // Kullanıcıya doğrulama e-postası gönderin
        emailSenderService.registerMail(savedUser.getEmail(), user.getKey());

        return savedUser;
    }

    public User login(User user) {

        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

    }


    public boolean findByKey(String key) {
        User user = userRepository.findByKey(key);
        if (user != null) {
            user.setActive(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
