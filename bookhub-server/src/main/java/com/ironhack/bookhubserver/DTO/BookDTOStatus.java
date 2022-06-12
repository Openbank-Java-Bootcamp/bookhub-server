package com.ironhack.bookhubserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTOStatus {
    @NotNull
    private String status;
    private int pagesRead;
    @NotNull
    private String id;
    private String title;
    private String imagen;
    private String pages;
}
