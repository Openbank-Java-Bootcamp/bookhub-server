package com.ironhack.bookhubserver.Controllers.Implementation;

import com.ironhack.bookhubserver.Controllers.Interface.BookControllerInterface;
import com.ironhack.bookhubserver.DTO.BookDTOStatus;
import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Service.Interface.BookServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookController implements BookControllerInterface {
    //delete book
    //edit book status
    //edit book pages read

    @Autowired
    BookServiceInterface bookServiceInterface;

    //post book
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@Valid @RequestBody BookDTOStatus DTOBook){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info("User asking for books: " +logEmail);
        log.info("Status parameter :" + DTOBook);
        bookServiceInterface.saveBookByUser(DTOBook, logEmail);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getUserBook(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info("User asking for books: " +logEmail);
       return  bookServiceInterface.getBookByUser(logEmail);
    }
}
