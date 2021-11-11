package com.pnu.cs.timeout.controller;

import com.pnu.cs.timeout.dto.UserDto;
import com.pnu.cs.timeout.dto.UserTransformer;
import com.pnu.cs.timeout.model.User;
import com.pnu.cs.timeout.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDto login(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User userByEmail = userService.readByEmail(userDto.getEmail());

        if (userByEmail != null && userByEmail.getPassword().equals(userDto.getPassword())) {
            return UserTransformer.toDto(userByEmail);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody UserDto userDto, BindingResult result) {
        return UserTransformer.toDto(userService.create(UserTransformer.toEntity(userDto)));
    }

}
