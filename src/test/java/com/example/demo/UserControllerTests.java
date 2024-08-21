package com.example.demo;

import static org.mockito.Mockito.when;
import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
import Controllers.UserController;
import Models.User;
import Services.UserService;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTests {

    @Mock
    private UserService userService; // Mock the UserService dependency

    @InjectMocks
    private UserController userController; // Inject the mock into the UserController

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test 
    public void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        User user1 = new User(1L, "FirstName1", "LastName1", "email1@example.com", "user1", "password1");
        User user2 = new User(2L, "FirstName2", "LastName2", "email2@example.com", "user2", "password2");
        mockUsers.add(user1);
        mockUsers.add(user2);
        when(userService.findAll()).thenReturn(mockUsers);

        
        ResponseEntity<List<User>> response = userController.getAllUsers();

        
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(mockUsers.size(), response.getBody().size());
        Assert.assertEquals(mockUsers, response.getBody());
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User(userId, "FirstName", "LastName", "email@example.com", "user", "password");
        when(userService.findById(userId)).thenReturn(mockUser);

        
        ResponseEntity<User> response = userController.getUserById(userId);

        
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(mockUser, response.getBody());
    }

    @Test
    public void testGetUserByUserName() {
        String username = "user";
        User mockUser = new User(1L, "FirstName", "LastName", "email@example.com", username, "password");
        when(userService.findByUserName(username)).thenReturn(mockUser);

        
        ResponseEntity<User> response = userController.getUserByUserName(username);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(mockUser, response.getBody());
    }

    @Test
    public void testCreateUser() {
        User newUser = new User("FirstName", "LastName", "email@example.com", "user", "password");
        User createdUser = new User(1L, "FirstName", "LastName", "email@example.com", "user", "password");
        when(userService.create(newUser)).thenReturn(createdUser);

        ResponseEntity<User> response = userController.create(newUser);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(createdUser, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User updatedUser = new User(userId, "FirstName", "LastName", "email@example.com", "user", "password");
        when(userService.update(userId, updatedUser)).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.update(userId, updatedUser);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(updatedUser, response.getBody());
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;
        when(userService.deleteById(userId)).thenReturn(true);

        ResponseEntity<Boolean> response = userController.delete(userId);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody());
    }

    @Test
    public void testDeleteUserByUserName() {
        String username = "user";
        when(userService.deleteByUserName(username)).thenReturn(true);

        ResponseEntity<Boolean> response = userController.deleteByUserName(username);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody());
    }
}

/*
@InjectMocks Annotation

Purpose: @InjectMocks is used to inject mock dependencies (i.e., 
objects annotated with @Mock) into the object being tested (in this
case, the UserController).

Usage:
You use @InjectMocks when you want to unit test a specific class in isolation by injecting mock dependencies into it.
This allows you to create a unit test for the UserController by injecting mock instances of dependencies like UserService.
This ensures that only the UserController is being tested, and its interactions with UserService are mocked and controlled

    @InjectMocks
    private UserController userController;

This tells Mockito to inject the mock UserService into UserController, 
enabling you to verify the behavior of UserController in isolation.

@MockBean Annotation

Purpose: @MockBean is a Spring Boot-specific annotation used to add a
 mock instance of a bean to the Spring application context.

Usage:
You use @MockBean when writing integration tests where you want the Spring context to be loaded, but still want to replace certain beans (like UserService) with mocks.
This allows you to test the behavior of your controller within a more realistic Spring context, but without relying on the actual implementation of the service layer.

    @MockBean
    private UserService userService;

This replaces the UserService bean in the Spring context with a mock, allowing you to focus on testing the controller in an environment closer to production.

Why Use @InjectMocks on UserController Instead of @MockBean?

Isolated Testing: @InjectMocks is preferred when you are focusing on unit testing the UserController alone, without involving Spring’s application context. This ensures that the controller is the only focus of the test, and interactions with dependencies are fully controlled by mocks.

Speed: Tests with @InjectMocks are usually faster because they do not require the Spring context to be loaded.

Clear Responsibility: Using @InjectMocks makes it clear that the test is meant to validate the logic inside the UserController, not the overall interaction within the Spring context.

When to Use @MockBean?

Integration Tests: When you need the Spring context to be available, but still want to mock certain beans like UserService.
Testing Multiple Layers: When your test needs to interact with other components managed by Spring (e.g., controllers, services, repositories), but you want to control or mock specific dependencies.

In summary, @InjectMocks is used for focused unit testing of a class by 
injecting mock dependencies directly, while @MockBean is used in Spring 
Boot integration tests to mock beans within the Spring context.

 */