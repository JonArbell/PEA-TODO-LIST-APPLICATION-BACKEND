package com.myapp.pea.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "users")
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    @Size(max = 25, message = "Username must be at most 25 characters long.")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @Column(nullable = false)
    private String password;

    @Size(max = 35, message = "Firstname must be at most 35 characters long.")
    @Column(nullable = false)
    private String firstName;

    @Size(max = 35, message = "Lastname must be at most 35 characters long.")
    @Column(nullable = false)
    private String lastName;

    @Email(message = "Please provide a valid email address.")
    @Column(unique = true,nullable = false)
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Todo> todos;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Lists> lists;

}
