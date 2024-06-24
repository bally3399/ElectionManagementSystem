package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CastBallotRequest {
    private Long voterId;
    private Long candidateId;

}
