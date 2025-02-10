package com.myapp.pea.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username; //Nullable

    @Column(nullable = false, unique = true)
    private String email;

    private String password; //Nullable

    @Column(nullable = false)
    private String fullName;

    @Column(length = 21, unique = true)
    private String googleId; //Nullable

    @Column(nullable = false)
    private String provider;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todoList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.myapp.pea.Entities.List> lists;

}
