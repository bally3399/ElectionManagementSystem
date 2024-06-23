package africa.semicolon.com.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CastBallotResponse {
    private Long voterId;
    private Long candidateId;
    private Long electionId;
}
