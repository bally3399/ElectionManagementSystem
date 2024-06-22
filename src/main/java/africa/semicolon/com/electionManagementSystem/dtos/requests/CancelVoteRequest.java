package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.Category;
import africa.semicolon.com.electionManagementSystem.models.Election;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelVoteRequest {
    private Long voteId;
    private Long adminId;
    private Election election;
    private Category category;
}

