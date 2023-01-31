package com.example.securitymaster.ds;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterUser {
    private String username;
    private String password;
    private String repeatedPassword;
}
