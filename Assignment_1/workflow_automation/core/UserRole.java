package core;

/*
 * Defines the hierarchical roles of Users in the system.
 */
public enum UserRole {
    JUNIOR(1),
    MANAGER(2),
    SENIOR(3);

    public final int level;

    UserRole(int level) {
        this.level = level;
    }
}