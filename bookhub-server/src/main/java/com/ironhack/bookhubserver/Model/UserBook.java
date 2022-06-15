package com.ironhack.bookhubserver.Model;

import com.ironhack.bookhubserver.Utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int numPages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private String dateAdd;

    public UserBook(Status status, int numPages, User user, Book book) {
        this.status = status;
        this.numPages = numPages;
        this.user = user;
        this.book = book;
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaActual = sdf.format(todayDate);
        dateAdd = fechaActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBook userBook = (UserBook) o;
        return numPages == userBook.numPages && status == userBook.status && Objects.equals(user, userBook.user) && Objects.equals(book, userBook.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, numPages, user, book);
    }
}
