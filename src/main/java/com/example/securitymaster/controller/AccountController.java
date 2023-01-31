package com.example.securitymaster.controller;

import com.example.securitymaster.ds.RegisterUser;
import com.example.securitymaster.security.validator.UserValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@ControllerAdvice(value = "com.example.securitymaster.controller")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/accounts")
    public String home(){
        return "account";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new RegisterUser());
        return "register";
    }

    @InitBinder
    public void initBinder(DataBinder binder){
        binder.addValidators(new UserValidator());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user")@Valid RegisterUser user, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        else {
            userDetailsManager.createUser(
                    new User(
                            user.getUsername(),
                            passwordEncoder.encode(user.getPassword()),
                            Collections.singletonList(
                                    new SimpleGrantedAuthority("USERS")
                            )
                    )
            );
        }
        return "redirect:/login";
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(final Throwable throwable, HttpServletResponse response) {
        // logger.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        //logger.info("==============================="+ errorMessage);
        System.out.println("===========================HelloWrold!");
        ModelAndView model=new ModelAndView();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        model.addObject("httpStatus",response.getStatus());
        model.addObject("errorMessage", errorMessage);
        model.addObject("exception",throwable);
        model.setViewName("error1");
        return model;
    }

}
