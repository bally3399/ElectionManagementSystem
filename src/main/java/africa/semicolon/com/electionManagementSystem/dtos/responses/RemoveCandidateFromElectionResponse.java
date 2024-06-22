package africa.semicolon.com.electionManagementSystem.dtos.responses;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveCandidateFromElectionResponse {
    private Long adminId;
    private Long electionId;
    private Long candidateId;

}
