package com.ironhack.bookhubserver.Service.Interface;

import com.ironhack.bookhubserver.Model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User userSignupDTO);

    List<User> getUsers();
}
