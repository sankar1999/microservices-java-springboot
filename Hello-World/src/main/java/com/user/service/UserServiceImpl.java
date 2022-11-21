package com.user.service;

import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User saveUserRecord(User user) {
        repository.save(user);
        return user;
    }

    @Override
    public User getSingleUser(String id) {
        return repository.findById(id).get();
        //

    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateEmailAddress(User user) {
        User requestObj = repository.findById(user.getId()).get();
        requestObj.setEmailId(user.getEmailId());
        repository.save(requestObj);
        return requestObj;
    }
}
