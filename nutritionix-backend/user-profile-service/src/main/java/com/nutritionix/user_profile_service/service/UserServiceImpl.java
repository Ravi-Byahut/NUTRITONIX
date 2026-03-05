package com.nutritionix.user_profile_service.service;

import com.nutritionix.user_profile_service.entity.UserCredential;
import com.nutritionix.user_profile_service.exception.UserAlreadyExistException;
import com.nutritionix.user_profile_service.exception.UserNotFoundException;
import com.nutritionix.user_profile_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


//    @Override
//    public User saveUser(User user) throws UserAlreadyExistException {
//        Optional<User> userResult = userRepository.findById(user.getId());
//
//        if (userResult.isPresent()) {
//            throw new UserAlreadyExistException("User already exists");
//        }else if (user.getId() == null) {
//            throw new UserAlreadyExistException("User Id cannot be empty");
//        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
//            throw new UserAlreadyExistException("Password cannot be empty");
//        } else if (user.getRole() == null) {
//            throw new UserAlreadyExistException("Role cannot be empty");
//        }
//
//        return userRepository.save(user);
//
//    }

    @Override
    public UserCredential findByUserId(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<UserCredential> getAllUser() {
        return userRepository.findAll();
    }

//    @Override
//    public User findByUserIdAndPassword(Long id, String password) throws UserNotFoundException {
//        User authUser = userRepository.findByIdAndPassword(id, password);
//        if (authUser == null) {
//            throw new UserNotFoundException("User not found");
//        }
//        return authUser;
//    }

}

