package com.testlog.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig   {

@Bean
public UserDetailsService  userDetailService(){

    UserDetails normalUser = User
    .withUsername("user")
    .password("password")
    .roles("ROLE_NORMAL")
    .build();

    UserDetails adminUser = User
    .withUsername("admin")
    .password("password")
    .roles("ROLE_ADMIN")
    .build();
    InMemoryUserDetailsManager inMemoryUserDetailsManager=  new InMemoryUserDetailsManager(normalUser,adminUser);
     return inMemoryUserDetailsManager;

}


    
@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{


        httpSecurity.csrf().disable()
        .authorizeHttpRequests().
        requestMatchers("/home/user")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin();

return httpSecurity.build();
   

}
}
