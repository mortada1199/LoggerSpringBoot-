package com.testlog.demo.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/home")
public class HomeController {
    
        @PostMapping("/login")
        public void login(){
                NarmalUser();
        }


@GetMapping("/normal")
public ResponseEntity<String> NarmalUser(){
        return ResponseEntity.ok("yes , i am Normal user");
}

@GetMapping("/admin")
public ResponseEntity<String> AdminUser(){
        return ResponseEntity.ok("yes , i am Admin user");
}


@GetMapping("/user")
public ResponseEntity<String> Users(){
        return ResponseEntity.ok("yes , i am  user");
}


}

