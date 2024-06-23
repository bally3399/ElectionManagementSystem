package africa.semicolon.com.electionManagementSystem.exceptions;

public class InvalidElectionStatusException extends RuntimeException {
    public InvalidElectionStatusException(String message) {
        super(message);
    }
}
