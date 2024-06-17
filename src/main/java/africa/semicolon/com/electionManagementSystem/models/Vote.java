package africa.semicolon.com.electionManagementSystem.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vote {
    private Long voteId;
    private Long ballotId;
    private Long candidateId;
}
