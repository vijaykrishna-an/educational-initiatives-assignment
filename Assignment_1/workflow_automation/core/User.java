package core;

import java.util.Objects;

/*
 * Represents a user. Added equals and hashCode for proper list/set management.
 */
public class User {
    private final String username;
    private final String password;
    private final UserRole role;

    public User(String username, String password, UserRole role) {
        // ... validation ...
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- Getters ---
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }

    // NEW: Essential for checking if a user is already in the 'involvedUsers' list.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

