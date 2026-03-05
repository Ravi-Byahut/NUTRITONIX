package com.nutritionix.user_profile_service.controller;

import com.nutritionix.user_profile_service.entity.UserCredential;
import com.nutritionix.user_profile_service.exception.UserNotFoundException;
import com.nutritionix.user_profile_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

   private  ResponseEntity<?> responseEntity;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            UserCredential user = userService.findByUserId(id);
            if (user == null) {
                throw new UserNotFoundException("User not found");
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

//    @PostMapping("login/user")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        try {
//            if (user.getId() == null || user.getPassword() == null) {
//                throw new UserNotFoundException();
//            }
//            User userDetails = userService.findByUserIdAndPassword(user.getId(), user.getPassword());
//            if (userDetails == null) {
//                throw new UserNotFoundException("User not found");
//            }
//            if (!(user.getPassword().equals(userDetails.getPassword()))) {
//                throw new UserNotFoundException("User not found");
//            }
//        } catch (UserNotFoundException e) {
//            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//        return responseEntity;
//    }

}
