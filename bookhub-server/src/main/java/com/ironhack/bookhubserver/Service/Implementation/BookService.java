package com.ironhack.bookhubserver.Service.Implementation;

import com.ironhack.bookhubserver.DTO.BookDTOStatus;
import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Repositories.BookRepository;
import com.ironhack.bookhubserver.Repositories.UserRepository;
import com.ironhack.bookhubserver.Service.Interface.BookServiceInterface;
import com.ironhack.bookhubserver.Utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;


    public void saveBook(BookDTOStatus dtoBook){
        if (bookRepository.findAll().size() != 0) {
            Optional<Book> bookDB = bookRepository.findByDbId(dtoBook.getId());
            Book newBook = new Book();
            if (bookDB.isEmpty()) {
                newBook = new Book(dtoBook.getId(), dtoBook.getImagen(), dtoBook.getTitle(), dtoBook.getPages());
                bookRepository.save(newBook);
            }
        }else{
            Book newBook = new Book(dtoBook.getId(), dtoBook.getImagen(), dtoBook.getTitle(), dtoBook.getPages());
            bookRepository.save(newBook);
        }
    }



    public void saveBookByUser(BookDTOStatus dtoBook, String email){
        /*
        Optional<Book> bookDB = bookRepository.findByDbIdAndStatus(dtoBook.getId(),Status.valueOf(dtoBook.getStatus()));
        User user = userRepository.findByEmail(email);
        List<Book> userBooks= user.getBooks();
        boolean alreadyHave = false;
        int removeIndex = -1;
        for (int i=0; i< userBooks.size(); i++) {
            if (userBooks.get(i).getDbId().equals(dtoBook.getId()) && userBooks.get(i).getStatus().toString().equals(dtoBook.getStatus())) {
                alreadyHave = true;
            } else if (userBooks.get(i).getDbId().equals(dtoBook.getId())) {
                removeIndex = i;
            }
        }
        if(!alreadyHave) {
            if (removeIndex != -1) {
                userBooks.remove(removeIndex);
            }
            if (bookDB.isEmpty()) {
                //save the book without user
                saveBook(dtoBook);
                //we updated the user List
                userBooks.add(bookRepository.findByDbIdAndStatus(dtoBook.getId(), Status.valueOf(dtoBook.getStatus())).get());
                user.setBooks(userBooks);
                userRepository.save(user);
            } else {
                userBooks.add(bookDB.get());
                user.setBooks((userBooks));
                userRepository.save(user);
            }
        }

         */
    }

    public List<Book> getBookByUser(String email){
        /*
        User user = userRepository.findByEmail(email);
        return user.getBooks();

         */
        return null;
    }
}

