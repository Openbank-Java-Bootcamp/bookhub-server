package com.ironhack.bookhubserver.Service.Implementation;

import com.ironhack.bookhubserver.DTO.RatingDTO;
import com.ironhack.bookhubserver.DTO.UpdateRatingDTO;
import com.ironhack.bookhubserver.Model.Book;
import com.ironhack.bookhubserver.Model.Rating;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Repositories.BookRepository;
import com.ironhack.bookhubserver.Repositories.RatingRepository;
import com.ironhack.bookhubserver.Repositories.UserRepository;
import com.ironhack.bookhubserver.Service.Interface.BookServiceInterface;
import com.ironhack.bookhubserver.Service.Interface.RatingServiceInterface;
import com.ironhack.bookhubserver.Utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RatingService implements RatingServiceInterface {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;
    public List<Rating> getRatingByBook(String id){
        List<Rating> ratingBook = ratingRepository.findRatingByDBId(id);
        if (ratingBook.size() == 0){
            log.info("not found the data");
        }
        return ratingBook;
    }

    public void saveRating(RatingDTO ratingDTO, String email){
       Optional<Book> bookDB = bookRepository.findByDbId(ratingDTO.getBookId());
       User user = userRepository.findByEmail(email);
       Rating newRating = new Rating();
       if (bookDB.isEmpty()){
           //create a new book
           Book newBook = new Book(ratingDTO.getBookId(), ratingDTO.getImagen(), ratingDTO.getTitle(), ratingDTO.getPages());
           bookRepository.save(newBook);
           //save the rating without the user
           newRating = new Rating(ratingDTO.getComment(), ratingDTO.getPoints(), newBook, user);
           ratingRepository.save(newRating);
           //posterior a guardalo llamos a una funcion que una rating y user y pista
       }else{
           //saving the rating without hte user
           newRating = new Rating(ratingDTO.getComment(), ratingDTO.getPoints(), bookDB.get(), user);
           ratingRepository.save(newRating);
       }
       //we update the user with the ratings new info
        //List<Rating> userRatings= user.getRatings();
        //userRatings.add(newRating);
        //user.setRatings(userRatings);
        userRepository.save(user);

    }

    public void deleteRating(long id, String logEmail){
        User user = userRepository.findByEmail(logEmail);
        Optional<Rating> ratingDB = ratingRepository.findById(id);
        if (ratingDB.isPresent()){
            if (ratingDB.get().getUser().equals(user)){
                ratingRepository.delete(ratingDB.get());
            }
        }
    }

    public Rating updateRating(long id, String logEmail, UpdateRatingDTO updateRatingDTO){
        User user = userRepository.findByEmail(logEmail);
        Optional<Rating> ratingDB = ratingRepository.findById(id);
        if (ratingDB.isPresent()){
            if (ratingDB.get().getUser().equals(user)){
                ratingDB.get().setComment(updateRatingDTO.getComment());
                ratingDB.get().setPoints(updateRatingDTO.getPoints());
            }
        }
        return ratingRepository.save(ratingDB.get());
    }
}
