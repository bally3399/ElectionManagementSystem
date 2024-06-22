package africa.semicolon.com.electionManagementSystem.exceptions;

public class ElectionNotFoundException extends RuntimeException{

    public ElectionNotFoundException(String message){
        super(message);
    }
}
