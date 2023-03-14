package com.testlog.demo.requests;

import com.testlog.demo.models.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter  @AllArgsConstructor @NoArgsConstructor @Getter

public class CreateUserRequest {
    @NotNull
    private String name;


    @NotNull
    private String type;
    @NotNull
    private UserType userType;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
