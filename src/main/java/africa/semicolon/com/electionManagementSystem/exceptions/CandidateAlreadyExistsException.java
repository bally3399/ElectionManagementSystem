package africa.semicolon.com.electionManagementSystem.exceptions;

public class CandidateAlreadyExistsException extends ElectionManagementException {
    public CandidateAlreadyExistsException(String message) {
        super(message);
    }
}
