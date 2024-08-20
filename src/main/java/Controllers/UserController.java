package Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import Models.User;
import Services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController  // Marks this class as a REST controller, making it ready to handle web requests.
@RequestMapping("/users")  // Specifies that all routes in this controller will start with "/users".
public class UserController {

    @Autowired  // Injects the `UserService` dependency automatically.
    private UserService userService;

    // Constructor injection of the UserService dependency.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")  // Handles GET requests to "/users".
    public ResponseEntity<List<User>> getAllUsers() { // Returns a List instead of an Iterable now due to change in the Service findAll() method
        // Calls the service to get all users and returns them with an HTTP 200 OK status.
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user")  // Handles GET requests to "/user" (likely meant to be a path with an ID).
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Calls the service to find a user by ID and returns it with an HTTP 200 OK status.
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
        // Calls the service to find a user by USERNAME and returns it with an HTTP 200 OK status.
        return new ResponseEntity<>(userService.findByUserName(username), HttpStatus.OK);
    }

    @PostMapping("/new_user")  // Handles POST requests to "/newUser".
    public ResponseEntity<User> create(@RequestBody User user) {
        // Calls the service to create a new user and returns the created user with an HTTP 201 Created status.
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }
    
    @PutMapping("/update_user/{id}")  // Handles PUT requests to "/updateUser/{id}".
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        // Calls the service to update an existing user by ID and returns the updated user with an HTTP 200 OK status.
        return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete_user/{id}")  // Handles DELETE requests to "/deleteUser/{id}".
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        // Calls the service to delete a user by ID and returns true if successful with an HTTP 200 OK status.
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete_user_2/{userName}")  // Handles DELETE requests to "/deleteUser/{userName}".
    public ResponseEntity<Boolean> deleteByUserName(@PathVariable String userName) {
        // Calls the service to delete a user by username and returns true if successful with an HTTP 200 OK status.
        return new ResponseEntity<>(userService.deleteByUserName(userName), HttpStatus.OK);
    }
}

