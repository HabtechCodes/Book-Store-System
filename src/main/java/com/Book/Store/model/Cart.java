package com.Book.Store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "cart"
            ,cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    private List<Books> booksList;

    @OneToOne(cascade = CascadeType.PERSIST
            ,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
