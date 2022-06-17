package com.ironhack.bookhubserver.Controllers.Implementation;

import com.ironhack.bookhubserver.Controllers.Interface.BookUserControllerInterface;
import com.ironhack.bookhubserver.DTO.BookDTOPages;
import com.ironhack.bookhubserver.DTO.DTOUserBookStatus;
import com.ironhack.bookhubserver.Model.UserBook;
import com.ironhack.bookhubserver.Service.Interface.BookUserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookUserController implements BookUserControllerInterface {

    @Autowired
    BookUserServiceInterface bookUserServiceInterface;


    //edit pages
    @PatchMapping("/books/pages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editPages(@PathVariable int id, @RequestBody @Valid BookDTOPages bookDT0Pages){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info("User asking for books: " +logEmail);
        bookUserServiceInterface.editPagesBook(id, bookDT0Pages, logEmail);
    }

    //get userbook by id
    @GetMapping("/bookuser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserBook getUserBook(@PathVariable long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info("User asking for books: " +logEmail);
        return bookUserServiceInterface.getUserBook(id, logEmail);
    }

    //edit status
    @PatchMapping("/bookuser/status/{id}")
    public UserBook editStatus(@PathVariable long id, @RequestBody @Valid DTOUserBookStatus dtoUserBookStatus){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info("User asking for books: " +logEmail);
        return bookUserServiceInterface.getEditStatus(id, logEmail, dtoUserBookStatus.getStatus());
    }
}
