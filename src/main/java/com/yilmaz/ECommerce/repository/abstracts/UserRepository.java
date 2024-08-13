package com.yilmaz.ECommerce.repository.abstracts;

import com.yilmaz.ECommerce.model.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{


    //bu methodu jpa kullanmadan manuel olarak yazmak istiyorum
    User findByEmail (String email);





    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

    User findByKey(String key);


}
