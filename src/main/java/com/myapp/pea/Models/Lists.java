package com.myapp.pea.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "Lists")
@Getter
@Setter
public class Lists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(nullable = false)
    @Size(max = 25, message = "List name must be at most 25 characters long.")
    private String listName;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "userId",updatable = false,insertable = false)
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "lists")
    private List<Todo> todos;

}
