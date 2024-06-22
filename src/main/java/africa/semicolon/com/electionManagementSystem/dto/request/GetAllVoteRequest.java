package africa.semicolon.com.electionManagementSystem.dto.request;

import africa.semicolon.com.electionManagementSystem.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAllVoteRequest {
    private Category category;
}
