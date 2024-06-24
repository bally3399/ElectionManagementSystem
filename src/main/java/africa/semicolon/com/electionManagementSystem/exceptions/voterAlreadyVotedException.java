package africa.semicolon.com.electionManagementSystem.exceptions;

public class voterAlreadyVotedException extends RuntimeException {
    public voterAlreadyVotedException(String message) {
        super(message);
    }
}
