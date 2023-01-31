package com.example.securitymaster.security.annotation.customers;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static com.example.securitymaster.security.SecurityRoles.*;
import org.springframework.security.access.annotation.Secured;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Secured(ROLES_PREFIX+CUSTOMERS_ADMIN)
public @interface CustomerAdmin {
}
