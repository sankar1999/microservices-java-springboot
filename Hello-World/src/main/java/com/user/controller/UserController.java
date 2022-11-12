package com.user.controller;

import com.user.model.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Hello World
    @GetMapping("/hello")
    public String helloController() {
        return "Hello World...";
    }

    // Returning Object
    @GetMapping("/getUser")
    public User user() {
        User justin = new User();
        justin.setId("101");
        justin.setName("Justin");
        justin.setEmailId("justin@gmail.com");
        return justin;
    }

   // Post Mapping
    @PostMapping("/saveSingleRecord")
    public User saveUserRecord(@RequestBody User user) {
        return userService.saveUserRecord(user);
    }

    // Get user by ID
    @GetMapping("/getUserDetail")
    public User gerSingleUser(@RequestParam("id") String id) {
        return userService.getSingleUser(id);
    }

    // Get All Users
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Update Email Address
    @PostMapping("/updateEmailAddress")
    public User updateEmailAddress(@RequestBody User user) {
        return userService.updateEmailAddress(user);
    }
}
