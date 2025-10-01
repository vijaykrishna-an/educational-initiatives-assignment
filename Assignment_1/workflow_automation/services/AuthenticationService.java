package services;

import core.User;
import core.UserRole;
import java.util.HashMap;
import java.util.Map;

/*
 * Manages user registration and login.
 */
public class AuthenticationService {
    private final Map<String, User> users = new HashMap<>();
    private WorkflowService workflowService;

    public void setWorkflowService(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    public User register(String username, String password, UserRole role) {
        if (users.containsKey(username)) {
            System.out.println("Error: Username '" + username + "' already exists.");
            return null;
        }
        User newUser = new User(username, password, role);
        users.put(username, newUser);

        // Notify workflow service about the new user for reassignment purposes
        if (workflowService != null) {
            workflowService.registerUser(newUser);
        }

        System.out.println("User '" + username + "' registered successfully as a " + role.name());
        return newUser;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Welcome, " + username + "!");
            return user;
        }
        System.out.println("Login failed: Invalid username or password.");
        return null;
    }
}