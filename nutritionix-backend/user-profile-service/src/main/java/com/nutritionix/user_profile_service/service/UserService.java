package com.nutritionix.user_profile_service.service;

import com.nutritionix.user_profile_service.entity.UserCredential;
import com.nutritionix.user_profile_service.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
//    User saveUser(User user) throws UserAlreadyExistException;
//
    UserCredential findByUserId(Integer id) throws UserNotFoundException;
//    User findByUserIdAndPassword(Long id, String password) throws UserNotFoundException;

    List<UserCredential> getAllUser();
}
