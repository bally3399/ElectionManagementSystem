package africa.semicolon.com.electionManagementSystem.dtos.responses;

import africa.semicolon.com.electionManagementSystem.models.Candidate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddVoteResponse {
    private Long id;
    private Long candidateId;



}
