package com.security.securitylession.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "ad bos olmamalidir")
    @Size(min = 4,max = 20,message = "ad en azi 4 en coxu 20 simvoldan ibaret olmalidir.")
    @Column(name = "username")
    private String userName;

    @NotBlank(message = "ad gunu bos olmamalidir")
    @Size(min = 4,max = 4,message = "dogum tarixi uygun deyil")
    @Column(name = "year_of_birth")
    private String yearOfBirth;

    @NotBlank(message = "sifre bos saxlanmamalidir")
    @Size(min = 5,message = "sifre uygun deyil,basqa sifre daxil edin")
    @Column(name = "password")
    private String password;


}
