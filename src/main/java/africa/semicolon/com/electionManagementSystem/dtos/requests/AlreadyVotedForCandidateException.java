package africa.semicolon.com.electionManagementSystem.dtos.requests;

public class AlreadyVotedForCandidateException extends RuntimeException {

    public AlreadyVotedForCandidateException(String message) {
        super(message);
    }
}
