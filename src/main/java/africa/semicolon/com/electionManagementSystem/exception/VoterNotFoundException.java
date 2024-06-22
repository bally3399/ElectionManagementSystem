package africa.semicolon.com.electionManagementSystem.exception;

public class VoterNotFoundException extends RuntimeException{
    public VoterNotFoundException(String message){
        super(message);
    }
}
