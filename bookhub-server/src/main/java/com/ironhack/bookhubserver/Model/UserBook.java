package com.ironhack.bookhubserver.Model;

import com.ironhack.bookhubserver.Utils.Status;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private Status status;

    private int numPages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
