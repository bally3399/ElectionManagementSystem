package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.Candidate;
import africa.semicolon.com.electionManagementSystem.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAllVoteRequest {
    private Long candidateId;
}
