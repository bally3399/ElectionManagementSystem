package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewVoterRequest {
    private Long id;
   private String voterNumber;
}
