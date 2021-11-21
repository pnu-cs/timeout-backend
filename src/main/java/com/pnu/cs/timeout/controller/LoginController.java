package com.pnu.cs.timeout.controller;

import com.pnu.cs.timeout.dto.UserDto;
import com.pnu.cs.timeout.dto.UserTransformer;
import com.pnu.cs.timeout.model.User;
import com.pnu.cs.timeout.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto) {
        User userByEmail = userService.readByEmail(userDto.getEmail());

        if (userByEmail != null && userByEmail.getPassword().equals(userDto.getPassword())) {
            return new ResponseEntity<>(userByEmail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(UserTransformer.toEntity(userDto), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        if (userService.readByEmail(userDto.getEmail()) == null) {
            User user = userService.create(UserTransformer.toEntity(userDto));
            return ResponseEntity.ok(UserTransformer.toDto(user));
        } else {
            return ResponseEntity.unprocessableEntity().body(userDto);
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
