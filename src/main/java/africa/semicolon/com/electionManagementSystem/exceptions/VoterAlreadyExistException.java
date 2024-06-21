package africa.semicolon.com.electionManagementSystem.exceptions;

public class VoterAlreadyExistException extends RuntimeException {
    public VoterAlreadyExistException(String message) {
        super(message);
    }
}
