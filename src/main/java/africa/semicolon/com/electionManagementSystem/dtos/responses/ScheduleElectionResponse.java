package africa.semicolon.com.electionManagementSystem.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ScheduleElectionResponse {
    private String electionId;
    private String title;
    private String location;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalTime startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalTime startTime;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalTime endDate;
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime endTime;

}
