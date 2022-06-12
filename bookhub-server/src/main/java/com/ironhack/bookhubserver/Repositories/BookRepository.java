package com.ironhack.bookhubserver.Repositories;

import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //find books by UserId && status

    //find bookby Id and Status


    Optional<Book> findByDbId(String id);




}
