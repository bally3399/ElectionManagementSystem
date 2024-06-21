package africa.semicolon.com.electionManagementSystem.dtos.reponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterVoterResponse {
    private boolean isLocked = true;
    private String message;

}
