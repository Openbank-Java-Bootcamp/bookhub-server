package com.ironhack.bookhubserver.Repositories;

import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    //querie pa sacar los userBook de un usuario
    List<UserBook> findByUser(User user);

    Optional<UserBook> findByUserAndBook(User user, Book book);
}
