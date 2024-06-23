package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.Candidate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddVoteRequest {
    private Long id;
    private Long candidateId;


}