package com.pnu.cs.timeout.service;

import com.pnu.cs.timeout.model.User;
import com.pnu.cs.timeout.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private static User expected;

    @BeforeAll
    public static void setUp() {
        expected = new User();
        expected.setId(1L);
        expected.setEmail("mike@gmail.com");
        expected.setPassword("Mike12345!");
        expected.setFirstName("Mykhailo");
        expected.setLastName("Pavliuk");
    }

    @Test
    public void createValidUserTest() {
        User actual = userService.create(expected);
        assertEquals(expected, actual);

        verify(userRepository, times(1)).save(expected);
    }

    @Test
    public void createNullUserTest() {
        assertNull(userService.create(null));

        verify(userRepository, times(0)).save(expected);
    }

    @Test
    public void readByIdExistingUserTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        assertEquals(expected, userService.readById(1L));

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void readByIdNotExistingUserTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertNull(userService.readById(100L));

        verify(userRepository, times(1)).findById(100L);
    }

    @Test
    public void updateExistingUserTest() {
        doReturn(true)
                .when(userRepository)
                .existsById(anyLong());

        doReturn(expected)
                .when(userRepository)
                .save(expected);

        assertEquals(expected, userService.update(expected));

        verify(userRepository, times(1)).save(any(User.class));
        verify(userRepository, times(1)).existsById(anyLong());
    }

    @Test
    public void updateNullUserTest() {
        assertNull(userService.update(null));

        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void updateNotExistingUserTest() {
        doReturn(false)
                .when(userRepository)
                .existsById(anyLong());

        expected.setId(100);
        assertNull(userService.update(expected));

        verify(userRepository, times(0)).save(any(User.class));
        verify(userRepository, times(1)).existsById(anyLong());
    }

    @Test
    public void deleteExistingUserTest() {
        doReturn(true)
                .when(userRepository)
                .existsById(anyLong());

        userService.delete(anyLong());

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void deleteNotExistingUserTest() {
        doReturn(false)
                .when(userRepository)
                .existsById(anyLong());

        userService.delete(anyLong());

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(0)).deleteById(anyLong());
    }

    @Test
    public void getAllUsersTest() {
        List<User> userList = new ArrayList<>();
        userList.add(expected);

        doReturn(userList)
                .when(userRepository)
                .findAll();

        assertEquals(userList, userService.getAll());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void readByEmailExistingUserTest() {
        doReturn(expected)
                .when(userRepository)
                .getUserByEmail(anyString());

        assertEquals(expected, userService.readByEmail(expected.getEmail()));

        verify(userRepository, times(1)).getUserByEmail(expected.getEmail());
    }

    @Test
    public void readByEmailNotExistingUserTest() {
        doReturn(null)
                .when(userRepository)
                .getUserByEmail(anyString());

        assertNull(userService.readByEmail(expected.getEmail()));

        verify(userRepository, times(1)).getUserByEmail(expected.getEmail());
    }
}
