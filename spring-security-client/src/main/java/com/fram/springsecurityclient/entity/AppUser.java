package com.fram.springsecurityclient.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Column(length = 60)
    private String password;

    private String role;
    private boolean enabled = false;
}