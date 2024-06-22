package africa.semicolon.com.electionManagementSystem.dtos.responses;

import africa.semicolon.com.electionManagementSystem.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAllVoteResponse {
    private Category category;


}
