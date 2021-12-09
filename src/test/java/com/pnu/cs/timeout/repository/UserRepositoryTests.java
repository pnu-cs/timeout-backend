package com.pnu.cs.timeout.repository;

import com.pnu.cs.timeout.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("mike1@gmail.com");
        user.setPassword("Mike12345!");
        user.setFirstName("Mykhailo");
        user.setLastName("Pavliuk");;

        userRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(user);
    }

    @Test
    public void getUserByExistingEmailTest() {
        Assertions.assertEquals(user, userRepository.getUserByEmail("mike1@gmail.com"));
    }

    @Test
    public void getUserByNotExistingEmailTest() {
        Assertions.assertNull(userRepository.getUserByEmail("mykhailo@mail.com"));
    }

}
