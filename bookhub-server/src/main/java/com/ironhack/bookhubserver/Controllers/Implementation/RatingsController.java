package com.ironhack.bookhubserver.Controllers.Implementation;

import com.ironhack.bookhubserver.Controllers.Interface.RatingControllerInterface;
import com.ironhack.bookhubserver.DTO.RatingDTO;
import com.ironhack.bookhubserver.DTO.UpdateRatingDTO;
import com.ironhack.bookhubserver.Model.Rating;
import com.ironhack.bookhubserver.Service.Interface.RatingServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class RatingsController implements RatingControllerInterface {

    @Autowired
    RatingServiceInterface ratingServiceInterface;

    //edit rating (only owner)
    //delete rating (only owner)


    //get rating by book
    @GetMapping("/ratings/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> getRatingOfBook(@PathVariable String bookId){
        log.info("Path Variable: " + bookId);
        return ratingServiceInterface.getRatingByBook(bookId);

    }

    //post rating
    @PostMapping("/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRating(@RequestBody  RatingDTO ratingDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info(logEmail);
        ratingServiceInterface.saveRating(ratingDTO, logEmail);
    }

    //delete rating by id
    @DeleteMapping("/ratings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRating(@PathVariable long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info(logEmail);
        ratingServiceInterface.deleteRating(id, logEmail);
    }

    //edit rating by id
    @PatchMapping("/rating/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rating updateRating(@RequestBody UpdateRatingDTO updateRatingDTO, @PathVariable long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String logEmail = auth.getName();
        log.info(logEmail);
        return ratingServiceInterface.updateRating(id, logEmail, updateRatingDTO);
    }
}
