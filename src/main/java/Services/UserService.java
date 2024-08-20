package Services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import Models.User;
import Repositories.UserRepository;

@Service  // Marks this class as a service component, so it can be detected and managed by Spring
public class UserService {

    @Autowired  // Automatically injects the UserRepository dependency into this field
    private UserRepository userRepository;

    // Constructor for UserService to initialize the userRepository field
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to retrieve all users from the repository
    public List<User> findAll() { // Method adjusted to return a list of all users
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> usersList = new ArrayList<>();
        usersIterable.forEach(usersList::add);
        return usersList; 
    }

    // Method to find a user by their ID
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);  // Attempts to find a user by ID, wrapped in an Optional

        if (optionalUser.isEmpty()) {  // Checks if the Optional is empty (i.e., user not found)
            return null;  // Returns null if the user is not found
        }

        User user = optionalUser.get();  // Retrieves the user from the Optional
        return user;  // Returns the found user
    }

    public User findByUserName(String username) {
        // Use the repository to find the user by username and wrap it in an Optional
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
    
        // If the user is found, return the User object
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            // Handle the case where the user is not found
            throw new UsernameNotFoundException("User with username " + username + " not found.");
            // Alternatively, you could return null or handle it in another way depending on your use case.
        }
    }

    // Method to create a new user
    public User create(User user) {
        return userRepository.save(user);  // Saves the new user to the repository and returns the saved user
    }

    // Method to delete a user by their ID
    public Boolean deleteById(Long id) {
        userRepository.deleteById(id);  // Deletes the user with the given ID from the repository
        return true;  // Returns true to indicate successful deletion
    }

    // // Method to delete a user by the user object
    // public Boolean deleteByUserName(User user) {
    //     userRepository.delete(user);  // Deletes the given user object from the repository
    //     return true;  // Returns true to indicate successful deletion
    // }

    public Boolean deleteByUserName(String username) {
        // Find the user by username
        User user = userRepository.findByUsername(username);
        
        // Check if the user exists
        if (user != null) {
            // Deletes the user object from the repository
            userRepository.delete(user);
            return true;  // Return true to indicate successful deletion
        }
        
        return false;  // Return false if the user was not found
    }

    // Method to update an existing user with new data
    public User update(Long id, User newUserData) {
        Optional<User> optionalUser = userRepository.findById(id);  // Finds the user by ID, wrapped in an Optional

        // Guard clause: checks if the user was found
        if (optionalUser.isEmpty()) {
            return null;  // Returns null if the user is not found
        }

        User originalUser = optionalUser.get();  // Retrieves the existing user from the Optional
        originalUser.setfirstName(newUserData.getfirstName());  // Updates the first name
        originalUser.setlastName(newUserData.getlastName());  // Updates the last name
        originalUser.setUserName(newUserData.getUserName());  // Updates the username
        originalUser.setPassword(newUserData.getPassword());  // Updates the password
        return userRepository.save(originalUser);  // Saves the updated user and returns the saved user
    }
}
