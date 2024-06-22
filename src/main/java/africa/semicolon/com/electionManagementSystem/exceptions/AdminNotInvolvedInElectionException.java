package africa.semicolon.com.electionManagementSystem.exceptions;

public class AdminNotInvolvedInElectionException extends RuntimeException {
    public AdminNotInvolvedInElectionException(String message) {
        super(message);
    }
}
