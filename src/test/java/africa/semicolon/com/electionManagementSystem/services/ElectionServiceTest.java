package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.*;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddCandidateToElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateFromElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.AdminNotInvolvedInElectionException;
import africa.semicolon.com.electionManagementSystem.exceptions.ElectionNotFoundException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionDateException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionTimeException;
import africa.semicolon.com.electionManagementSystem.models.Election;
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
public class ElectionServiceTest {

    @Autowired
    private ElectionService electionService;

    @Test
    public void electionCanBeScheduledTest() {
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(100L);
        scheduleElectionRequest.setCategory(NATIONAL);
        scheduleElectionRequest.setTitle("Lagos State Governorship Election");
        scheduleElectionRequest.setLocation("Lagos");
        scheduleElectionRequest.setStartDate("3/9/2024");
        scheduleElectionRequest.setStartTime("7:00");
        scheduleElectionRequest.setEndDate("15/9/2024");
        scheduleElectionRequest.setEndTime("23:00");
        ScheduleElectionResponse scheduleElectionResponse = electionService.scheduleElection(scheduleElectionRequest);

        assertThat(scheduleElectionResponse).isNotNull();
        assertEquals("Lagos State Governorship Election", scheduleElectionResponse.getTitle());
    }

    @Test
    public void invalidElectionStartDate_ThrowsExceptionTest() {
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(100L);
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
    public void invalidElectionEndTimeTestThrowsExceptionTest() {
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(100L);
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
        cancelElectionRequest.setElectionId(303L);
        cancelElectionRequest.setAdminId(100L);
        CancelElectionResponse cancelElectionResponse = electionService.cancelElection(cancelElectionRequest);

        assertThat(cancelElectionResponse).isNotNull();
        assertEquals(303L, cancelElectionResponse.getElectionId());
        assertEquals(100L, cancelElectionResponse.getAdminId());
    }

    @Test
    public void nonExistentElection_CancelledThrowsExceptionTest() {
        CancelElectionRequest cancelElectionRequest = new CancelElectionRequest();
        cancelElectionRequest.setElectionId(500L);
        cancelElectionRequest.setAdminId(100L);

        assertThrows(ElectionNotFoundException.class,()->electionService.cancelElection(cancelElectionRequest));
    }

    @Test
    public void uninvolvedAdminCancelsElectionThrowsExceptionTest() {
        CancelElectionRequest cancelElectionRequest = new CancelElectionRequest();
        cancelElectionRequest.setElectionId(301L);
        cancelElectionRequest.setAdminId(101L);

        assertThrows(AdminNotInvolvedInElectionException.class, ()->electionService.cancelElection(cancelElectionRequest));
    }

    @Test
    public void addCandidateToElectionTest() {
        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
        addCandidateToElectionRequest.setAdminId(100L);
        addCandidateToElectionRequest.setCandidateId(400L);
        addCandidateToElectionRequest.setElectionId(300L);
        AddCandidateToElectionResponse addCandidateToElectionResponse = electionService.addCandidateToElection(addCandidateToElectionRequest);

        Election election = electionService.getElectionById(300L);

        /*
        assertThat(addCandidateToElectionResponse).isNotNull();
        assertEquals(1, election.getCandidates().size());
        assertEquals(400L, election.getCandidates().getFirst().getId());
        */
    }

    @Test
    public void candidateAddedTo_NonExistentElectionThrowsExceptionTest() {
        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
        addCandidateToElectionRequest.setAdminId(100L);
        addCandidateToElectionRequest.setCandidateId(400L);
        addCandidateToElectionRequest.setElectionId(500L);

        assertThrows(ElectionNotFoundException.class, ()->electionService.addCandidateToElection(addCandidateToElectionRequest));
    }

    @Test
    public void nonExistentCandidate_AddedToElectionThrowsExceptionTest() {
        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
        addCandidateToElectionRequest.setAdminId(100L);
        addCandidateToElectionRequest.setCandidateId(500L);
        addCandidateToElectionRequest.setElectionId(300L);

        //assertThrows(need to know the exception, ()->electionService.addCandidateToElection(addCandidateToElectionRequest));
    }

    @Test
    public void uninvolvedAdmin_AddsCandidateToElectionThrowsExceptionTest() {
        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
        addCandidateToElectionRequest.setAdminId(101L);
        addCandidateToElectionRequest.setCandidateId(400L);
        addCandidateToElectionRequest.setElectionId(300L);

        assertThrows(AdminNotInvolvedInElectionException.class, ()->electionService.addCandidateToElection(addCandidateToElectionRequest));

    }

    @Test
    public void removeCandidateFromElectionTest() {
        RemoveCandidateFromElectionRequest removeCandidateFromElectionRequest = new RemoveCandidateFromElectionRequest();
        removeCandidateFromElectionRequest.setCandidateId(403L);
        removeCandidateFromElectionRequest.setAdminId(100L);
        removeCandidateFromElectionRequest.setElectionId(303L);
        RemoveCandidateFromElectionResponse removeCandidateFromElectionResponse = electionService.removeCandidateFromElection(removeCandidateFromElectionRequest);

        assertThat(removeCandidateFromElectionResponse).isNotNull();
        assertEquals(403L, removeCandidateFromElectionResponse.getCandidateId());
        assertEquals(100L, removeCandidateFromElectionResponse.getAdminId());
    }

    @Test
    public void uninvolvedAdmin_RemovesCandidateFromElection_ThrowsExceptionTest() {
        RemoveCandidateFromElectionRequest removeCandidateFromElectionRequest = new RemoveCandidateFromElectionRequest();
        removeCandidateFromElectionRequest.setCandidateId(403L);
        removeCandidateFromElectionRequest.setAdminId(101L);
        removeCandidateFromElectionRequest.setElectionId(303L);

        assertThrows(AdminNotInvolvedInElectionException.class ,()->electionService.removeCandidateFromElection(removeCandidateFromElectionRequest));
    }

    @Test
    public void removeCandidate_FromNonExistentElection_ThrowsExceptionTest() {
        RemoveCandidateFromElectionRequest removeCandidateFromElectionRequest = new RemoveCandidateFromElectionRequest();
        removeCandidateFromElectionRequest.setCandidateId(403L);
        removeCandidateFromElectionRequest.setAdminId(100L);
        removeCandidateFromElectionRequest.setElectionId(303L);

        assertThrows(AdminNotInvolvedInElectionException.class ,()->electionService.removeCandidateFromElection(removeCandidateFromElectionRequest));
    }

    @Test
    public void electionCanBeUpdatedTest() {
        UpdateElectionRequest updateElectionRequest = new UpdateElectionRequest();


    }

}