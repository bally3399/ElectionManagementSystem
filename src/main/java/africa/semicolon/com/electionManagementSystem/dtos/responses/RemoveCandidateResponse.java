package africa.semicolon.com.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveCandidateResponse {
    private String message;

    public RemoveCandidateResponse() {
        this.message = message;
    }
}
