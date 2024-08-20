package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import Models.User;

public class UserModelUnitTests {

@Test
public void testGettersAndSetters() {
    // Create a User object with sample data
    User user = new User();
    user.setUserId(1L);
    user.setfirstName("John");
    user.setlastName("Doe");
    user.setEmail("john.doe@example.com");
    user.setUserName("johndoe");
    user.setPassword("securepassword");
    
    // Assert that getters return the expected values
    Assert.assertEquals(1L, user.getUserId());
    Assert.assertEquals("John", user.getfirstName());
    Assert.assertEquals("Doe", user.getlastName());
    Assert.assertEquals("john.doe@example.com", user.getEmail());
    Assert.assertEquals("johndoe", user.getUserName());
    Assert.assertEquals("securepassword", user.getPassword());
}

@Test
public void testEqualsAndHashCode() {
    User user1 = new User(1L, "John", "Doe", "john.doe@example.com", "johndoe", "securepassword");
    User user2 = new User(1L, "John", "Doe", "john.doe@example.com", "johndoe", "securepassword");
    User user3 = new User(2L, "Jane", "Doe", "jane.doe@example.com", "janedoe", "differentpassword");
    
    // Test equality
    Assert.assertTrue(user1.equals(user2)); // Same data, should be equal
    Assert.assertFalse(user1.equals(user3)); // Different data, should not be equal
    Assert.assertFalse(user1.equals(null)); // Null check
    Assert.assertFalse(user1.equals("String")); // Type check
    
    // Test hashCode
    Assert.assertEquals(user1.hashCode(), user2.hashCode()); // Same data, should have the same hash code
    Assert.assertNotEquals(user1.hashCode(), user3.hashCode()); // Different data, should have different hash codes
}

@Test
public void testDefaultConstructor() {
    User user = new User(); // Using the default constructor
    
    // Check that default values are initialized (typically, these would be null for objects and 0 for primitives)
    Assert.assertEquals(0L, user.getUserId());
    Assert.assertNull(user.getfirstName());
    Assert.assertNull(user.getlastName());
    Assert.assertNull(user.getEmail());
    Assert.assertNull(user.getUserName());
    Assert.assertNull(user.getPassword());
}

}

