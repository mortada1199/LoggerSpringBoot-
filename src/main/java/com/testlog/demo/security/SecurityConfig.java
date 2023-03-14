package com.testlog.demo.security;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig   {
@Bean
public PasswordEncoder  passwordEncoder(){
    return new BCryptPasswordEncoder();
}


// @Bean
// public UserDetailsService  userDetailService(){
//     UserDetails normalUser = User
//     .withUsername("user")
//     .password(passwordEncoder().encode("password"))
//     .roles("NORMAL")
//     .build();
//     UserDetails adminUser = User
//     .withUsername("admin")
//     .password(passwordEncoder().encode("password"))
//     .roles("ADMIN")
//     .build();
//     return new InMemoryUserDetailsManager(normalUser,adminUser);
// }


    
@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{


        httpSecurity.csrf().disable()
        .authorizeHttpRequests()
        // .requestMatchers("/home/normal")
        // .hasRole("NORMAL")
        // .requestMatchers("/home/admin")
        // .hasRole("ADMIN")
        .requestMatchers("/api/user/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin();

    
return httpSecurity.build();
   

}
}
