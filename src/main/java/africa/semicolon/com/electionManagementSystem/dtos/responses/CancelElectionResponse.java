package africa.semicolon.com.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelElectionResponse {
    private Long electionId;
    private Long adminId;


    public CancelElectionResponse(String electionCancelledSuccessfully) {
    }
}
