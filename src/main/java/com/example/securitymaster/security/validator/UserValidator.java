package com.example.securitymaster.security.validator;

import com.example.securitymaster.ds.RegisterUser;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterUser registerUser = (RegisterUser) target;
        if (!registerUser.getRepeatedPassword().equals(registerUser.getPassword())){
            errors.rejectValue("password","null","Password and Repeated Password must be the same.");
        }
    }
}
