package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.FindElectionRequest;
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


    @Test
    public void electionCanBeUpdatedTest() {
        UpdateElectionRequest updateElectionRequest = new UpdateElectionRequest();
        updateElectionRequest.setElectionId(302L);
        updateElectionRequest.setLocation("Kwara");
        updateElectionRequest.setTitle("Kwara state Governorship election");
        updateElectionRequest.setEndDate("9/9/2024");
        updateElectionRequest.setEndTime("21:00");
        UpdateElectionResponse updateElectionResponse = electionService.updateElection(updateElectionRequest);
        Election election = electionService.getElectionById(302L);

        assertThat(updateElectionResponse).isNotNull();
        assertEquals("Kwara", election.getLocation());
        assertEquals("Kwara state Governorship election", election.getTitle());
    }

    @Test
    public void nonExistentElectionUpdated_ThrowsExceptionTest() {
        UpdateElectionRequest updateElectionRequest = new UpdateElectionRequest();
        updateElectionRequest.setElectionId(503L);
        updateElectionRequest.setLocation("Kwara");
        updateElectionRequest.setTitle("Kwara state Governorship election");
        updateElectionRequest.setEndDate("9/9/2024");
        updateElectionRequest.setEndTime("21:00");

        assertThrows(ElectionNotFoundException.class, ()->electionService.updateElection(updateElectionRequest));
    }

    @Test
    public void findElectionTest() {
        FindElectionRequest findElectionRequest = new FindElectionRequest();
        findElectionRequest.setElectionId(300L);
        FindElectionResponse findElectionResponse = electionService.findElection(findElectionRequest);

        assertThat(findElectionResponse).isNotNull();
        assertEquals(300L, findElectionResponse.getElectionId());
    }

    @Test
    public void findNonExistentElectionTest() {
        FindElectionRequest findElectionRequest = new FindElectionRequest();
        findElectionRequest.setElectionId(500L);

        assertThrows(ElectionNotFoundException.class, ()->electionService.findElection(findElectionRequest));

    }

}