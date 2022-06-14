package com.ironhack.bookhubserver.Service.Implementation;

import com.ironhack.bookhubserver.DTO.BookDTOPages;
import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Model.UserBook;
import com.ironhack.bookhubserver.Repositories.BookRepository;
import com.ironhack.bookhubserver.Repositories.UserBookRepository;
import com.ironhack.bookhubserver.Repositories.UserRepository;
import com.ironhack.bookhubserver.Service.Interface.BookUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookUserService implements BookUserServiceInterface {

    @Autowired
    UserBookRepository userBookRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    public void editPagesBook(long id, BookDTOPages bookDT0Pages, String logEmail){
        Optional<Book> bookDB = bookRepository.findById(id);
        User user = userRepository.findByEmail(logEmail);
        if (bookDB.isPresent()) {
            Optional<UserBook> userBookDB = userBookRepository.findByUserAndBook(user,bookDB.get());
            if (userBookDB.isPresent()){
                userBookDB.get().setNumPages(bookDT0Pages.getPages());
                userBookRepository.save(userBookDB.get());
            }
        }
    }
}
