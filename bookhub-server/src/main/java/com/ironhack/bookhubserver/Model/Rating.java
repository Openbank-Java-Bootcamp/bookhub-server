package com.ironhack.bookhubserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String comment;
    private int points;
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Rating(String comment, int points, Book book, User user){
        this.comment = comment;
        this.points = points;
        this.publicationDate = new Date();
        this.book = book;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", book=" + book +
                ", comment='" + comment + '\'' +
                ", points=" + points +
                ", publicationDate=" + publicationDate +
                ", user=" + user +
                '}';
    }
}
