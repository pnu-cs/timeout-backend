package com.pnu.cs.timeout.service;

import com.pnu.cs.timeout.dto.UserDto;
import com.pnu.cs.timeout.model.User;

import java.util.List;

public interface UserService {
    UserDto create(UserDto dto);
    User readById(long id);
    User update(User user);
    void delete(long id);
    List<User> getAll();
    UserDto readByEmail(String email);
}
