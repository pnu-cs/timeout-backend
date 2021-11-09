package com.pnu.cs.timeout.service.impl;

import com.pnu.cs.timeout.model.User;
import com.pnu.cs.timeout.repository.UserRepository;
import com.pnu.cs.timeout.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (user != null) {
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        if (user != null) {
            if (userRepository.existsById(user.getId())) {
                return userRepository.save(user);
            }
        }

        return null;
    }

    @Override
    public void delete(long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public User readByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
