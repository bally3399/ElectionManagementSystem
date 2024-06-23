package africa.semicolon.com.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCandidateToElectionResponse {
    private Long electionId;
    private Long candidateId;
}
