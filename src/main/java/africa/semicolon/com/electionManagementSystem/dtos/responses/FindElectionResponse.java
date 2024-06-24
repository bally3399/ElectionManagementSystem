package africa.semicolon.com.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FindElectionResponse {

    private Long electionId;
    private LocalDate startDate;
    private LocalDate endDate;
}
