package com.yilmaz.ECommerce.model.concretes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    @Column(unique = true, nullable = false)
    private String username;
    private String email;
    private String password;
    @Column(name = "`key`")
    private String key;
    private Date createdAt = new Date();
    private boolean isActive = false;
    //delivery address
    private String address;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
