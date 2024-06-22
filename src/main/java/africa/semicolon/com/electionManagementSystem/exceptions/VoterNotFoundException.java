package africa.semicolon.com.electionManagementSystem.exceptions;

public class VoterNotFoundException extends RuntimeException{
    public VoterNotFoundException(String message){
        super(message);
    }
}
