package com.example.demo;

public class MockitoTestGround {

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

}
