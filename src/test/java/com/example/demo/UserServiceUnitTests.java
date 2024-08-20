package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import Models.User;
import Repositories.UserRepository;
import Services.UserService;
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

        User mockUser = new User(mockFirstName, mockLastName, mockEmail, mockUserName, mockPassword, null);
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

}

