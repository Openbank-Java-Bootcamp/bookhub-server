package com.ironhack.bookhubserver.Service.Interface;

import com.ironhack.bookhubserver.DTO.RatingDTO;
import com.ironhack.bookhubserver.DTO.UpdateRatingDTO;
import com.ironhack.bookhubserver.Model.Rating;

import java.util.List;

public interface RatingServiceInterface {
    List<Rating> getRatingByBook(String id);

    void saveRating(RatingDTO ratingDTO, String email);

    void deleteRating(long id, String logEmail);

    Rating updateRating(long id, String logEmail, UpdateRatingDTO updateRatingDTO);
}
