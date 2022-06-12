package com.ironhack.bookhubserver.Service.Interface;

import com.ironhack.bookhubserver.DTO.BookDTOStatus;
import com.ironhack.bookhubserver.Model.Book;

import java.util.List;

public interface BookServiceInterface {

    void saveBook(BookDTOStatus dtoBook);

    void saveBookByUser(BookDTOStatus dtoBook, String email);

    List<Book> getBookByUser(String email);
}
