package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveCandidateRequest {
    private Long adminId;
    private Long id;

}
