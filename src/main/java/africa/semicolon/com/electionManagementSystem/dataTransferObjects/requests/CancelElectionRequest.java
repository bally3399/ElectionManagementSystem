package africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelElectionRequest {

    private Long electionId;
    private Long adminId;

}
