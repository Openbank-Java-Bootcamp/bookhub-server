package com.ironhack.bookhubserver.Controllers.Interface;

import com.ironhack.bookhubserver.DTO.RatingDTO;
import com.ironhack.bookhubserver.DTO.UpdateRatingDTO;
import com.ironhack.bookhubserver.Model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RatingControllerInterface {

    //chequear el nombre del bd id en la base de datos

    //get rating by book

    List<Rating> getRatingOfBook( String bookId);
    //post rating

   void addRating(RatingDTO ratingDTO);


    void deleteRating(long id);

    Rating updateRating(UpdateRatingDTO updateRatingDTO, long id);
}
