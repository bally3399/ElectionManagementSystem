package africa.semicolon.com.electionManagementSystem.dtos.requests;


import africa.semicolon.com.electionManagementSystem.models.Category;
import africa.semicolon.com.electionManagementSystem.models.ElectionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateElectionStatusRequest {

    private Long electionId;
    private ElectionStatus electionStatus;
    private Long adminId;

}
