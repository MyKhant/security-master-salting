package com.example.securitymaster.ds;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "code cannot be empty!")
    @Pattern(regexp = "[A-Za-z]*",message = "code cannot contains illegal characters.")
    private String code;
    @NotBlank(message = "name cannot be empty!")
    @Pattern(regexp = "[A-Za-z]*",message = "name cannot contains illegal characters.")
    private String name;
    @NotBlank(message = "country cannot be empty!")
    @Pattern(regexp = "[A-Za-z]*",message = "country cannot contains illegal characters.")
    private String country;

    public Department(){

    }

    public Department(String code, String name, String country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }
}
