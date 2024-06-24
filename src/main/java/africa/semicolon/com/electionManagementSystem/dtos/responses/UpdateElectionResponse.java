package africa.semicolon.com.electionManagementSystem.dtos.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateElectionResponse {
    private String electionId;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime startTime;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime endTime;
}
