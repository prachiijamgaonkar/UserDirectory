package com.edu.myapp.dto;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private long mobile;  

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;  

    @Column(nullable = false)
    private Role role; 
}
