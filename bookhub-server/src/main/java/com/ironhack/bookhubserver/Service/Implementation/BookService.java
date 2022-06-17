package com.ironhack.bookhubserver.Service.Implementation;

import com.ironhack.bookhubserver.BookhubServerApplication;
import com.ironhack.bookhubserver.DTO.BookDTOStatus;
import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Model.UserBook;
import com.ironhack.bookhubserver.Repositories.BookRepository;
import com.ironhack.bookhubserver.Repositories.UserBookRepository;
import com.ironhack.bookhubserver.Repositories.UserRepository;
import com.ironhack.bookhubserver.Service.Interface.BookServiceInterface;
import com.ironhack.bookhubserver.Utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBookRepository userBooksRepository;


    //Saving a book (consecuence of adding a rating)
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

    //Saving a book (added by a user)
    //CASE1: no added yet in the database
    //CASE2: already in the database but the user does not have it
    //CASE 3: already in the database and the user have it in another state
    //CASE 4: already in the database and the user have it in that state (http badrequest)
    public void saveBookByUser(BookDTOStatus dtoBook, String email) {
        Optional<Book> bookDB = bookRepository.findByDbId(dtoBook.getId());
        User user = userRepository.findByEmail(email);
        //querie para sacar los books de un user
        List<UserBook> userBooks = user.getUserbook();
        boolean userHasIT = false;
        boolean userHastItDiffStatus = false;
        int index = -1;
        if (bookDB.isEmpty()){
            saveBook(dtoBook);
            Book book = bookRepository.findByDbId(dtoBook.getId()).get();
            //creation new userbook
            UserBook newUserBook = new UserBook(Status.valueOf(dtoBook.getStatus()), dtoBook.getPagesRead(), user, book);
            userBooksRepository.save(newUserBook);
            //update de user
            List<UserBook> newUserBooks = user.getUserbook();
            newUserBooks.add(newUserBook);
            user.setUserbook(newUserBooks);
            userRepository.save(user);
            //update book
        }else{
            //aqui acabo lo viejo
            for (int i = 0; i< userBooks.size(); i++){
                //this shit is not working
                //THE BOOK IS IN THE TABE BOOK NOW WE NEED TO CHECK IS IT IS IN THE USER BOOKS
                if (userBooks.get(i).getBook().getDbId() == bookDB.get().getDbId()){
                    userHasIT = true;
                    //THE USER HAVE IT (WE NEED TO SEE IF WE HAVE TO MODIFY THE STATUS)
                    if (Status.valueOf(dtoBook.getStatus()) != userBooks.get(i).getStatus()) {
                        userHastItDiffStatus = true;
                        index = i;
                    }else{
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The book is already save in this state");
                    }
                    //THE USER DOES NOT HAVE IT !
                }
            }
            if (!userHasIT){
                UserBook newUserBook = new UserBook(Status.valueOf(dtoBook.getStatus()), dtoBook.getPagesRead(), user, bookDB.get());
                userBooksRepository.save(newUserBook);
                //UPDATE USRE
                userBooks.add(newUserBook);
                user.setUserbook(userBooks);
                userRepository.save(user);
                //UPDATE BOOK
                //bookDB.get().getUserbook().add(newUserBook);
                //bookRepository.save(bookDB.get());
            }else if (userHasIT && userHastItDiffStatus){
                userHastItDiffStatus = true;
                userBooks.get(index).setStatus(Status.valueOf(dtoBook.getStatus()));
                userBooks.get(index).setNumPages(dtoBook.getPagesRead());
                userBooksRepository.save(userBooks.get(index));
            }
        }

    }

    //get the books of a user in a status
    public List<Book> getBookByUser(String email, String status) {
        User user = userRepository.findByEmail(email);
        List<UserBook> userBooks = user.getUserbook();
        List<Book> books = new ArrayList<>();
        if (userBooks.size() != 0){
            for (int i = 0; i < userBooks.size(); i++) {
                if (userBooks.get(i).getStatus() == Status.valueOf(status))
                books.add(userBooks.get(i).getBook());
            }
        }
        return books;
    }

    //delete a book the user has save (userbook) by id.
    public void deleteBookFromUser(long isbn, String logEmail){
        Optional<Book> bookDB = bookRepository.findById(isbn);
        User user = userRepository.findByEmail(logEmail);
        List<UserBook> userBooks = user.getUserbook();
        if (bookDB.isPresent()) {
            Optional<UserBook> userBookDB = userBooksRepository.findByUserAndBook(user,bookDB.get());
            if (userBookDB.isPresent()){
                if (userBooks.size() != 0){
                    int index = -1;
                    //remove the user
                    for (int i = 0; i < userBooks.size(); i++) {
                        if (userBooks.get(i).equals(userBookDB.get())){
                            index = i;
                        }
                    }
                    if(index != -1) {
                        userBooks.remove(index);
                        userRepository.save(user);
                    }
                    int index2 = -1;
                    for (int i = 0; i < bookDB.get().getUserbook().size(); i++) {
                        if (bookDB.get().getUserbook().get(i).equals(userBookDB.get())){
                            index = i;
                        }
                    }
                    if(index2 != -1) {
                        bookDB.get().getUserbook().remove(index2);
                        bookRepository.save(bookDB.get());
                    }
                }
                userBooksRepository.deleteById(userBookDB.get().getId());
                //userBooksRepository.delete(userBookDB.get());
            }
        }
    }
}

