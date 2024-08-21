package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import Models.User;
import Repositories.UserRepository;
import Services.UserService;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserServiceUnitTests {

    @MockBean // Mock the UserRepository to isolate service layer tests
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void whenUserNameIsProvided_thenRetrievedUserNameIsCorrect() {
        String mockUserName = "Username";

        User mockUser = new User();
        mockUser.setUserName(mockUserName);

        Mockito.when(userRepository.findByUsername(mockUserName)).thenReturn(mockUser);

        String testName = userService.findByUserName(mockUserName).getUserName();

        Assert.assertEquals(mockUserName, testName);
    }


    @Test
    public void whenUserIdIsProvided_thenRetrievedUserIsCorrect() {
        Long mockId = 2L;
        String mockFirstName = "FirstName";
        String mockLastName = "LastName";
        String mockEmail = "Email@email.email";
        String mockUserName = "Username";
        String mockPassword = "Qq!1";

        User mockUser = new User(mockFirstName, mockLastName, mockEmail, mockUserName, mockPassword);
        mockUser.setUserId(mockId);

        Mockito.when(userRepository.findById(mockId)).thenReturn(Optional.of(mockUser));

        User retrievedUser = userService.findById(mockId);

        Assert.assertNotNull(retrievedUser);
        Assert.assertEquals(mockFirstName, retrievedUser.getfirstName());
        Assert.assertEquals(mockLastName, retrievedUser.getlastName());
        Assert.assertEquals(mockEmail, retrievedUser.getEmail());
        Assert.assertEquals(mockUserName, retrievedUser.getUserName());
        Assert.assertEquals(mockPassword, retrievedUser.getPassword());
    }

    @Test
    public void whenNewUserIsProvided_thenUserIsCreated() {
        User mockUser = new User("FirstName", "LastName", "Email@email.email", "Username", "Qq!1");

        Mockito.when(userRepository.save(mockUser)).thenReturn(mockUser);

        User createdUser = userService.create(mockUser);

        Assert.assertNotNull(createdUser);
        Assert.assertEquals(mockUser.getUserName(), createdUser.getUserName());
    }

    @Test
    public void whenUserIsUpdated_thenUserDetailsAreUpdated() {
        Long mockId = 2L;
        User existingUser = new User("OldFirstName", "OldLastName", "old.email@email.email", "OldUsername", "OldPassword");
        existingUser.setUserId(mockId);

        User updatedUser = new User("NewFirstName", "NewLastName", "new.email@email.email", "NewUsername", "NewPassword");
        updatedUser.setUserId(mockId);

        Mockito.when(userRepository.findById(mockId)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.update(mockId, updatedUser);

        Assert.assertNotNull(result);
        Assert.assertEquals(updatedUser.getfirstName(), result.getfirstName());
        Assert.assertEquals(updatedUser.getlastName(), result.getlastName());
        Assert.assertEquals(updatedUser.getEmail(), result.getEmail());
        Assert.assertEquals(updatedUser.getUserName(), result.getUserName());
        Assert.assertEquals(updatedUser.getPassword(), result.getPassword());
    }

    @Test
    public void whenUserIsDeletedById_thenReturnTrue() {
        Long mockId = 2L;

        Mockito.when(userRepository.existsById(mockId)).thenReturn(true);
        Mockito.doNothing().when(userRepository).deleteById(mockId);

        boolean isDeleted = userService.deleteById(mockId);

        Assert.assertTrue(isDeleted);
    }

    @Test
    public void whenUserIsDeletedByUsername_thenReturnTrue() {
        String mockUserName = "Username";

        Mockito.when(userRepository.findByUsername(mockUserName)).thenReturn(new User());
        Mockito.doNothing().when(userRepository).findByUsername(mockUserName);

        boolean isDeleted = userService.deleteByUserName(mockUserName);

        Assert.assertTrue(isDeleted);
    }

    @Test
    public void whenFindAllUsers_thenReturnUserList() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User("FirstName1", "LastName1", "email1@example.com", "user1", "password1"));
        mockUsers.add(new User("FirstName2", "LastName2", "email2@example.com", "user2", "password2"));

        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userService.findAll();

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void whenUserIdDoesNotExist_thenReturnNull() {
        Long mockId = 99L;

        Mockito.when(userRepository.findById(mockId)).thenReturn(Optional.empty());

        User result = userService.findById(mockId);

        Assert.assertNull(result);
    }

    @Test
    public void whenUserNameDoesNotExist_thenReturnNull() {
        String mockUserName = "NonExistentUser";

        Mockito.when(userRepository.findByUsername(mockUserName)).thenReturn(null);

        User result = userService.findByUserName(mockUserName);

        Assert.assertNull(result);
    }

}

