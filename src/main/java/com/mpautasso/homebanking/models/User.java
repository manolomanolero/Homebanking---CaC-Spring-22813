package com.mpautasso.homebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 40, unique = true)
    private String email;
    @Column(nullable = false, length = 40, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private Integer age;

    //TODO accounts relations, one user can have many accounts
    /*@OneToMany
    @JoinColumn()*/
}
