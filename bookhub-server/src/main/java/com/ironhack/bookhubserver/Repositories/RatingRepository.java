package com.ironhack.bookhubserver.Repositories;

import com.ironhack.bookhubserver.Model.Rating;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM rating JOIN book ON rating.book_id = book.id WHERE book.db_id =:bookId")
    List<Rating> findRatingByDBId(String bookId);
}
