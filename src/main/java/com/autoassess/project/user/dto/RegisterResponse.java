package com.autoassess.project.user.dto;


import lombok.Data;

@Data
public class RegisterResponse {

    private Long userId;
    private String name;
    private String email;
    private String message;
}
