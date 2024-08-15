package com.yilmaz.ECommerce.repository.abstracts;

import com.yilmaz.ECommerce.model.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsernameAndPassword(String username, String password);

    User findByKey(String key);


}
