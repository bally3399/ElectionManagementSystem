package africa.semicolon.com.electionManagementSystem.exceptions;

public class AlreadyVotedForCandidateException extends RuntimeException {

    public AlreadyVotedForCandidateException(String message) {
        super(message);
    }
}
