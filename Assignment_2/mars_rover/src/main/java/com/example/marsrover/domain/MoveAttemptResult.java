package com.example.marsrover.domain;

/**
 * Represents the result of a rover's attempt to move forward.
 */
public enum MoveAttemptResult {
    SUCCESS,
    BLOCKED_BY_OBSTACLE,
    OUTSIDE_GRID_BOUNDARY
}