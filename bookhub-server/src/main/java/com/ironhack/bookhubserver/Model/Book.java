package com.ironhack.bookhubserver.Model;

import com.ironhack.bookhubserver.Utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dbId;
    private Date addDate;

    private String imagen;
    private String title;
    private String pages;

    @OneToMany(fetch = FetchType.EAGER)
    List<UserBook> userbook = new ArrayList<>();


    public Book(String dbId, String imagen, String title, String pages) {
        this.dbId = dbId;
        this.imagen = imagen;
        this.title = title;
        this.pages = pages;
        this.addDate = new Date();
    }




}
