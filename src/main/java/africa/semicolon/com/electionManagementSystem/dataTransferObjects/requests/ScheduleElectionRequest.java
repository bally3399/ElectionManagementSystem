package africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests;

import africa.semicolon.com.electionManagementSystem.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleElectionRequest {
    private String location;
    private String startDate;
    private String startTime;
    private String endTime;
    private String endDate;
    private String title;
    private Category category;
}
