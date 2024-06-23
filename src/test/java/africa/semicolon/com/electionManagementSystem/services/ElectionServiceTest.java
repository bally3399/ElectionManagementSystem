package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateElectionStatusRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;

import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
import africa.semicolon.com.electionManagementSystem.exceptions.*;
import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.models.ElectionStatus;
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
    public void electionStatusCanBeUpdatedTest() {
        UpdateElectionStatusRequest updateElectionStatusRequest = new UpdateElectionStatusRequest();
        updateElectionStatusRequest.setElectionId(303L);
        updateElectionStatusRequest.setAdminId(100L);
        updateElectionStatusRequest.setElectionStatus(ElectionStatus.ONGOING);
        UpdateElectionStatusResponse updateElectionStatusResponse = electionService.updateElectionStatus(updateElectionStatusRequest);
        Election election = electionService.getElectionById(303L);

        assertThat(updateElectionStatusResponse).isNotNull();
        assertEquals(303L, updateElectionStatusResponse.getElectionId());
        assertEquals(ElectionStatus.ONGOING, election.getElectionStatus());
    }

    @Test
    public void nonExistentElectionStatus_UpdatedThrowsExceptionTest() {
        UpdateElectionStatusRequest updateElectionStatusRequest = new UpdateElectionStatusRequest();
        updateElectionStatusRequest.setElectionId(500L);
        updateElectionStatusRequest.setAdminId(100L);

        assertThrows(ElectionNotFoundException.class,()->electionService.updateElectionStatus(updateElectionStatusRequest));
    }


//    @Test
//    public void addCandidateToElectionTest() {
//        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
//        addCandidateToElectionRequest.setAdminId(100L);
//        addCandidateToElectionRequest.setCandidateId(400L);
//        addCandidateToElectionRequest.setElectionId(300L);
//        AddCandidateToElectionResponse addCandidateToElectionResponse = electionService.addCandidateToElection(addCandidateToElectionRequest);
//
//        Election election = electionService.getElectionById(300L);
//
//        assertThat(addCandidateToElectionResponse).isNotNull();
//        assertEquals(1, election.getCandidates().size());
//        assertEquals(400L, election.getCandidates().getFirst().getId());
//    }
//
//    @Test
//    public void candidateAddedTo_NonExistentElectionThrowsExceptionTest() {
//        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
//        addCandidateToElectionRequest.setAdminId(100L);
//        addCandidateToElectionRequest.setCandidateId(400L);
//        addCandidateToElectionRequest.setElectionId(500L);
//
//        assertThrows(ElectionNotFoundException.class, ()->electionService.addCandidateToElection(addCandidateToElectionRequest));
//    }
//
//    @Test
//    public void nonExistentCandidate_AddedToElectionThrowsExceptionTest() {
//        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
//        addCandidateToElectionRequest.setAdminId(100L);
//        addCandidateToElectionRequest.setCandidateId(500L);
//        addCandidateToElectionRequest.setElectionId(300L);
//
//        assertThrows(CandidateNotFoundException.class, ()->electionService.addCandidateToElection(addCandidateToElectionRequest));
//    }
//
//    @Test
//    public void throwsExceptionTest(){
//        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
//        addCandidateToElectionRequest.setAdminId(100L);
//        addCandidateToElectionRequest.setCandidateId(402L);
//        addCandidateToElectionRequest.setElectionId(300L);
//
//        assertThrows(CandidateInElectionException.class, ()->electionService.addCandidateToElection(addCandidateToElectionRequest));
//    }
//
//    @Test
//    public void uninvolvedAdmin_AddedToElectionThrowsExceptionTest() {
//        AddCandidateToElectionRequest addCandidateToElectionRequest = new AddCandidateToElectionRequest();
//        addCandidateToElectionRequest.setAdminId(101L);
//        addCandidateToElectionRequest.setCandidateId(402L);
//        addCandidateToElectionRequest.setElectionId(300L);
//
//        assertThrows(InvalidElectionAdminException.class, ()->electionService.addCandidateToElection(addCandidateToElectionRequest));
//    }
//
//    @Test
//    public void removeCandidateFromElectionTest() {
//        RemoveCandidateFromElectionRequest removeCandidateFromElectionRequest = new RemoveCandidateFromElectionRequest();
//        removeCandidateFromElectionRequest.setElectionId(300L);
//        removeCandidateFromElectionRequest.setAdminId(100L);
//        removeCandidateFromElectionRequest.setCandidateId(402L);
//        RemoveCandidateFromElectionResponse removeCandidateFromElectionResponse = electionService.removeCandidateFromElection(removeCandidateFromElectionRequest);
//
//        assertThat(removeCandidateFromElectionResponse).isNotNull();
//    }

    @Test
    public void electionCanBeUpdatedTest() {
        UpdateElectionRequest updateElectionRequest = new UpdateElectionRequest();

    }

}