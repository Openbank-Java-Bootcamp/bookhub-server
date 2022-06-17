package com.ironhack.bookhubserver.Service.Implementation;

import com.ironhack.bookhubserver.DTO.BookDTOPages;
import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Model.UserBook;
import com.ironhack.bookhubserver.Repositories.BookRepository;
import com.ironhack.bookhubserver.Repositories.UserBookRepository;
import com.ironhack.bookhubserver.Repositories.UserRepository;
import com.ironhack.bookhubserver.Service.Interface.BookUserServiceInterface;
import com.ironhack.bookhubserver.Utils.Status;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BookUserService implements BookUserServiceInterface {

    @Autowired
    UserBookRepository userBookRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    //edit pages read in a userbook
    public void editPagesBook(long id, BookDTOPages bookDT0Pages, String logEmail){
        Optional<Book> bookDB = bookRepository.findById(id);
        User user = userRepository.findByEmail(logEmail);
        if (bookDB.isPresent()) {
            Optional<UserBook> userBookDB = userBookRepository.findByUserAndBook(user, bookDB.get());
            if (userBookDB.isPresent()) {
                userBookDB.get().setNumPages(bookDT0Pages.getPages());
                userBookRepository.save(userBookDB.get());
            }
        }
    }

    //edit status in a userbook
    public UserBook getEditStatus(long id, String logEmail, String status){
        Optional<Book> bookDB = bookRepository.findById(id);
        User user = userRepository.findByEmail(logEmail);
        if (bookDB.isPresent()) {
            Optional<UserBook> userBookDB = userBookRepository.findByUserAndBook(user, bookDB.get());
            if (userBookDB.isPresent()) {
                userBookDB.get().setStatus(Status.valueOf(status));
                return userBookRepository.save(userBookDB.get());
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No userbook foud");
    }

    //get an user book by book and user
    public UserBook getUserBook(long id, String logEmail){
        Optional<Book> bookDB = bookRepository.findById(id);
        User user = userRepository.findByEmail(logEmail);
        if (bookDB.isPresent()) {
            return userBookRepository.findByUserAndBook(user,bookDB.get()).get();
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No userbook foud");
        }
    }
}
