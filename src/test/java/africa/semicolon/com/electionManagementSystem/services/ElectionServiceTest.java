package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests.CancelElectionRequest;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests.ScheduleElectionRequest;

import africa.semicolon.com.electionManagementSystem.dataTransferObjects.responses.CancelElectionResponse;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionDateException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionTimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static africa.semicolon.com.electionManagementSystem.models.Category.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
@Slf4j
public class ElectionServiceTest {

    @Autowired
    private ElectionService electionService;

    @Test
    public void electionCanBeScheduledTest(){
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setCategory(NATIONAL);
        scheduleElectionRequest.setTitle("Lagos State Governorship Election");
        scheduleElectionRequest.setLocation("Lagos");
        scheduleElectionRequest.setStartDate("1/9/2024");
        scheduleElectionRequest.setStartTime("7:00");
        scheduleElectionRequest.setEndDate("5/9/2024");
        scheduleElectionRequest.setEndTime("23:00");
        ScheduleElectionResponse scheduleElectionResponse = electionService.scheduleElection(scheduleElectionRequest);

        assertThat(scheduleElectionResponse).isNotNull();
        assertEquals("Lagos State Governorship Election", scheduleElectionResponse.getTitle());
    }

    @Test
    public void invalidElectionStartDateTestThrowsExceptionTest(){
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setCategory(NATIONAL);
        scheduleElectionRequest.setTitle("Lagos State Governorship Election");
        scheduleElectionRequest.setLocation("Lagos");
        scheduleElectionRequest.setStartDate("invalid date");
        scheduleElectionRequest.setStartTime("7:00");
        scheduleElectionRequest.setEndDate("5/9/2024");
        scheduleElectionRequest.setEndTime("23:00");

        assertThrows(InvalidElectionDateException.class ,()->electionService.scheduleElection(scheduleElectionRequest));
    }

    @Test
    public void invalidElectionEndTimeTestThrowsExceptionTest(){
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setCategory(NATIONAL);
        scheduleElectionRequest.setTitle("Lagos State Governorship Election");
        scheduleElectionRequest.setLocation("Lagos");
        scheduleElectionRequest.setStartDate("1/9/2024");
        scheduleElectionRequest.setStartTime("7:00");
        scheduleElectionRequest.setEndDate("5/9/2024");
        scheduleElectionRequest.setEndTime("invalid end time");

        assertThrows(InvalidElectionTimeException.class ,()->electionService.scheduleElection(scheduleElectionRequest));
    }


    @Test
    public void electionCanBeCancelledTest() {
        CancelElectionRequest cancelElectionRequest = new CancelElectionRequest();
        cancelElectionRequest.setElectionId(100L);
        cancelElectionRequest.setAdminId(200L);

        CancelElectionResponse cancelElectionResponse = electionService.cancelElection(cancelElectionRequest);


    }
}