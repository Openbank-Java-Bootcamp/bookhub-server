package com.ironhack.bookhubserver.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    @NotNull
    private String bookId;
    @NotNull
    private String comment;
    @NotNull
    private int points;

    private String title;
    private String imagen;
    private String pages;

}
