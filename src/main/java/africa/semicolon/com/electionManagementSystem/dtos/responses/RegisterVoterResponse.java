package africa.semicolon.com.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterVoterResponse {
    private boolean isLocked = true;
    private String message;

}
