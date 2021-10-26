package com.pnu.cs.timeout.controller;

import com.pnu.cs.timeout.dto.UserDto;
import com.pnu.cs.timeout.model.User;
import com.pnu.cs.timeout.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public HttpStatus login(@Validated @ModelAttribute("user") UserDto userDto, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }

        User userByEmail = userService.readByEmail(userDto.getEmail());

        if (userByEmail != null && userByEmail.getPassword().equals(userDto.getPassword())) {
            model.addAttribute("user", userByEmail);
            return HttpStatus.OK;
        } else {
            model.addAttribute("user", userDto);
            return HttpStatus.NOT_FOUND;
        }
    }

}
