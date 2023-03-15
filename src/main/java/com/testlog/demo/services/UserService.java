package com.testlog.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testlog.demo.Repo.UserRepo;
import com.testlog.demo.controllers.UserController;
import com.testlog.demo.models.User;
import com.testlog.demo.requests.CreateUserRequest;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    Logger logger =LoggerFactory.getLogger(UserService.class);


    public User create(CreateUserRequest userRequest) {

        if(!userRequest.getName().isEmpty()){
            logger.info("the name is {}", userRequest.getName());
            logger.error("errrrrrrrooooorrrrrrrrrrrrrrrrrrrr");
            logger.debug(null, userRequest, userRequest);
            
        }

        
        User user = new User(0, userRequest.getName(), userRequest.getType(),userRequest.getEmail(),userRequest.getUserType(),userRequest.getPassword());
        User result = userRepo.save(user);
        return result;
    }

    
}
