package com.autoassess.project.user.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
