package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCandidateToElectionRequest {
    private Long adminId;
    private Long candidateId;
    private Long electionId;
}
