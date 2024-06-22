package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateElectionRequest {

    private Long electionId;
    private String title;
    private String location;
    private boolean isRegistrationOpen;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private Category category;
    private Long adminId;
    private ElectionStatus electionStatus;

}
