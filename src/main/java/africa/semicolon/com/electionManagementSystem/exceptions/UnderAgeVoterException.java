package africa.semicolon.com.electionManagementSystem.exceptions;

public class UnderAgeVoterException extends RuntimeException {
    public UnderAgeVoterException(String message){
        super(message);
    }
}
