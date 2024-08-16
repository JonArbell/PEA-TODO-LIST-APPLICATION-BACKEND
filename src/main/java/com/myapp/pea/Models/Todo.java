package com.myapp.pea.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "todos")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(nullable = false)
    private boolean done;

    @Column(nullable = false)
    private String formattedDate;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime dateModified;

    @Size(max = 200, message = "Description must be at most 200 characters long.")
    private String shortDescription;

    @Size(max = 35, message = "Title must be at most 35 characters long.")
    @Column(nullable = false)
    private String title;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "userId",updatable = false,insertable = false)
    private User user;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "listsId")
    private Lists lists;

}
