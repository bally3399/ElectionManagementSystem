package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CastVoteRequest {
    private Long voterId;
    private Long candidateId;
    private Long electionId;

}
