package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.Category;
import africa.semicolon.com.electionManagementSystem.models.Party;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddVoteRequest {
    private Party party;
    private String voterId;
    private Category category;
    private long electionId;

}
