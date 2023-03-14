package com.testlog.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;

import com.testlog.demo.models.User;
import com.testlog.demo.services.CustomUserDetailsService;
import com.testlog.demo.services.TokenService;
import com.testlog.demo.requests.CreateUserRequest;
import com.testlog.demo.responses.BasicResponse;
import com.testlog.demo.services.UserService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService  userService;

    @Autowired 
    private CustomUserDetailsService customUserDetailService; 
    


    @PostMapping(name = "/")
    public @ResponseBody ResponseEntity<?> insert(@RequestBody @Valid CreateUserRequest input) {
        User user = userService.create(input);// call function in services
        if (user != null) {
            return new ResponseEntity<>(new BasicResponse("created successfully", "000", user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new BasicResponse("created fail", "999", null), HttpStatus.OK);
        }
    }

   

    private static  final Logger logger= LoggerFactory.getLogger(UserController.class);
    private  final TokenService tokenService;


    public UserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public  String token(Authentication authentication)
    {
        logger.debug("token request for user:'{}'",authentication.getName());
        String token = tokenService.generateToken(authentication);
        logger.debug("Token granded {}",token);
        return token;
    }
    // public @ResponseBody ResponseEntity<?> loginByUserName(@RequestBody  CreateUserRequest input) {
    //     UserDetails userDetails=customUserDetailService.loadUserByUsername(input.getName());
    //     if(userDetails !=null){

        

    //         return new ResponseEntity<>(new BasicResponse("authenticated", "000", userDetails), HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(new BasicResponse("created fail", "999", null), HttpStatus.OK);
    //     }
    // }







}
