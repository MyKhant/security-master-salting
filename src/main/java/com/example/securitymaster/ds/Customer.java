package com.example.securitymaster.ds;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "code cannot be blank.")
    @Size(min = 2,max = 5,message = "code must be between 2 and 5")
    @Pattern(regexp = "[A-Za-z]*",message = "code cannot contains illegal characters.")
    private String code;
    @NotBlank(message = "firstName cannot be empty.")
    @Pattern(regexp = "[A-Za-z-]*",message = "firstName cannot contains illegal characters.")
    private String firstName;
    @NotBlank(message = "lastName cannot be empty.")
    @Pattern(regexp = "[A-Za-z-]*",message = "lastName cannot contains illegal characters.")
    private String lastName;
    @NotBlank(message = "address cannot be empty.")
    @Pattern(regexp = "[\\w .\\-/,]*",message = "address cannot contains illegal characters.")
    private String address;

    public Customer(){

    }

    public Customer(String code, String firstName, String lastName, String address) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
