package africa.semicolon.com.electionManagementSystem.dtos.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelElectionRequest {

    private Long electionId;
    private Long adminId;

}
