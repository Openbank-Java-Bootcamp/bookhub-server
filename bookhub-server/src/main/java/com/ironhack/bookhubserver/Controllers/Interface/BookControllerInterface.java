package com.ironhack.bookhubserver.Controllers.Interface;

import com.ironhack.bookhubserver.Model.Book;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookControllerInterface {
    List<Book> getUserBookStatus(String status);
}
