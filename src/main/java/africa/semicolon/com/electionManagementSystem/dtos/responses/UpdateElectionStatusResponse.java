package africa.semicolon.com.electionManagementSystem.dtos.responses;

import africa.semicolon.com.electionManagementSystem.models.ElectionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateElectionStatusResponse {
    private Long electionId;
    private Long adminId;
    private ElectionStatus electionStatus;

}
