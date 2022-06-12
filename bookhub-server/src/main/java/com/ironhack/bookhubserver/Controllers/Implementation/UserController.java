package com.ironhack.bookhubserver.Controllers.Implementation;

import com.google.gson.Gson;
import com.ironhack.bookhubserver.DTO.UserVerifyDTO;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Repositories.UserRepository;
import com.ironhack.bookhubserver.Service.Implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    //post user
    //get user ? (login stuff)
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public String verifyToken(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User userFromDb = userRepository.findByEmail(email);
        UserVerifyDTO userVerifyDTO = new UserVerifyDTO(userFromDb.getName(), userFromDb.getEmail());
        Gson gson = new Gson();
        String userDetails = gson.toJson(userVerifyDTO);
        return userDetails;
    }
}
