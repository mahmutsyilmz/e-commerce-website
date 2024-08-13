package com.yilmaz.ECommerce.model.dto.requests.userRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;

    // getters and setters
}