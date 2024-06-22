package africa.semicolon.com.electionManagementSystem.exceptions;

public class InvalidElectionAdminException extends RuntimeException{
    public InvalidElectionAdminException(String message){
        super(message);
    }
}
