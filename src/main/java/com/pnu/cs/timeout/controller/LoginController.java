package com.pnu.cs.timeout.controller;

import com.pnu.cs.timeout.dto.UserDto;
import com.pnu.cs.timeout.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto userDto) {
        return userService.readByEmail(userDto.getEmail());
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

}
