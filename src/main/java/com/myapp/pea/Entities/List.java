package com.myapp.pea.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "LISTS")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String listName;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "list")
    private java.util.List<Todo> todoList;

}
