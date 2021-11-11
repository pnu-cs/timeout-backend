package com.pnu.cs.timeout.service.impl;

import com.pnu.cs.timeout.dto.UserDto;
import com.pnu.cs.timeout.model.User;
import com.pnu.cs.timeout.repository.UserRepository;
import com.pnu.cs.timeout.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = UserDto.toEntity(dto);

        User saved = userRepository.save(user);
        return UserDto.toDto(saved);
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        if (user != null && userRepository.existsById(user.getId())) {
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public void delete(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public UserDto readByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        return UserDto.toDto(user);
    }
}
