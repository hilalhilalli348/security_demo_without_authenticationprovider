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

    @Column(name = "username")
    @NotBlank(message = "xana bos ola bilmez.")
    @Size(min = 3,max = 50,message = "ad en az 3 en cox 50 simvol ola biler.")
    private String userName;


    @Column(name = "year_of_birth")
    @Size(min = 1900,max = 2010,message = "dogum ili [1900;2010] arasi kecerlidir.")
    private String yearOfBirth;

    @Column(name = "password")
    private String password;


}
