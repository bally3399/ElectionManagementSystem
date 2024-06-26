package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleElectionRequest {
    private Long adminId;
    private String location;
    private String startDate;
    private String startTime;
    private String endTime;
    private String endDate;
    private String title;
    private Category category;

}
