package com.user.service;

import com.user.model.User;

import java.util.List;

public interface UserService {
    User saveUserRecord(User user);

    User getSingleUser(String id);

    List<User> getAllUsers();
}
