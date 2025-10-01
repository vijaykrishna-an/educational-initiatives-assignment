package core;

/*
 * Defines the priority levels for a Task, with an associated numeric value.
 */
public enum PriorityLevel {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    public final int value;

    PriorityLevel(int value) {
        this.value = value;
    }

    public static PriorityLevel fromValue(int value) {
        for (PriorityLevel level : values()) {
            if (level.value == value) {
                return level;
            }
        }
        return null;
    }
}