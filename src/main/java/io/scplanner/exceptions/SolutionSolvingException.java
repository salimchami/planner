package io.scplanner.exceptions;

public class SolutionSolvingException extends Exception {

    public SolutionSolvingException(String message) {
        super(message);
    }

    public SolutionSolvingException(String message, Throwable t) {
        super(message, t);
    }
}
