package com.testlog.demo.services;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.testlog.demo.Repo.UserRepo;
import com.testlog.demo.models.User;
@Service
    public class CustomUserDetailsService implements UserDetailsService {
      @Autowired
      private UserRepo userRepo;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
          /*Here we are using dummy data, you need to load user data from
           database or other third party application*/
          User user = findUserbyUername(username);
      
          UserBuilder builder = null;
          if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles("ADMIN");
          } else {
            throw new UsernameNotFoundException("User not found.");
          }
      
          return builder.build();
        }
      
        private User findUserbyUername(String username) {
          Optional<User> user=userRepo.findByName(username);
          if(user.isPresent()){
            return user.get();
          }
       
          return null;
        }
      }
    