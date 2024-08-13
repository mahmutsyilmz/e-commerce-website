package com.yilmaz.ECommerce.model.dto.requests.userRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String surname;
    private String email;
    private String password;

    // getters and setters
}