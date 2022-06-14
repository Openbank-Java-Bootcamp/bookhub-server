package com.ironhack.bookhubserver.Controllers.Implementation;

import com.ironhack.bookhubserver.Controllers.Interface.BookUserControllerInterface;
import com.ironhack.bookhubserver.DTO.BookDTOPages;
import com.ironhack.bookhubserver.Service.Interface.BookUserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookUserController implements BookUserControllerInterface {

    @Autowired
    BookUserServiceInterface bookUserServiceInterface;

    //edit number of read pages

    @PatchMapping("/books/{id}")
    public void editPages(@PathVariable int id, @RequestBody BookDTOPages bookDT0Pages){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info("User asking for books: " +logEmail);
        bookUserServiceInterface.editPagesBook(id, bookDT0Pages, logEmail);
    }
}
