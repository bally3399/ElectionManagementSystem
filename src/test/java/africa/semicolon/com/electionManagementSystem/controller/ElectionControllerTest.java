package africa.semicolon.com.electionManagementSystem.controller;


import africa.semicolon.com.electionManagementSystem.dtos.requests.*;

import africa.semicolon.com.electionManagementSystem.dtos.responses.AddCandidateToElectionResponse;

import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.UpdateElectionStatusResponse;
import africa.semicolon.com.electionManagementSystem.models.ElectionStatus;
import africa.semicolon.com.electionManagementSystem.services.ElectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static africa.semicolon.com.electionManagementSystem.models.Category.NATIONAL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
public class ElectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testElectionsSucceds() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(100L);
        scheduleElectionRequest.setCategory(NATIONAL);
        scheduleElectionRequest.setTitle("Lagos State Governorship Election");
        scheduleElectionRequest.setLocation("Lagos");
        scheduleElectionRequest.setStartDate("3/9/2024");
        scheduleElectionRequest.setStartTime("7:00");
        scheduleElectionRequest.setEndDate("15/9/2024");
        scheduleElectionRequest.setEndTime("23:00");

        mockMvc.perform(post("/api/v1/election/scheduleElection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(scheduleElectionRequest))
        ).andExpect(status().isCreated()).andDo(print());

    }

    @Test
    public void testUpdateElectionStatus() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateElectionStatusRequest updateElectionStatusRequest = new UpdateElectionStatusRequest();
        updateElectionStatusRequest.setElectionId(303L);
        updateElectionStatusRequest.setAdminId(100L);
        updateElectionStatusRequest.setElectionStatus(ElectionStatus.ONGOING);

        mockMvc.perform(post("/api/v1/election/updateElectionStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(updateElectionStatusRequest))
        ).andExpect(status().isOk()).andDo(print());
    }


    @Test
    public void testUpdateElection() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateElectionRequest updateElectionRequest = new UpdateElectionRequest();
        updateElectionRequest.setElectionId(302L);
        updateElectionRequest.setLocation("Kwara");
        updateElectionRequest.setTitle("Kwara state Governorship election");


        mockMvc.perform(post("/api/v1/election/updateElection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(updateElectionRequest))
        ).andExpect(status().isOk()).andDo(print());
    }



//    @Test
//    void scheduleElection() {
//        ScheduleElectionRequest request = new ScheduleElectionRequest();
//        request.setElectionName("Election Name");
//        request.setStartTime("10:00");
//        request.setEndTime("12:00");
//        request.setStartDate("1/1/2022");
//        request.setEndDate("1/2/2022");
//
//        ScheduleElectionResponse response = new ScheduleElectionResponse();
//        response.setMessage("Election scheduled successfully");
//        when(electionService.scheduleElection(request)).thenReturn(response);
//        ResponseEntity<ScheduleElectionResponse> result = electionController.scheduleElection(request);
//        assertEquals(HttpStatus.CREATED, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    void cancelElection() {
//        CancelElectionRequest request = new CancelElectionRequest();
//        request.setElectionId(1L);
//
//        CancelElectionResponse response = new CancelElectionResponse();
//        response.setMessage("Election cancelled successfully");
//
//        when(electionService.cancelElection(request)).thenReturn(response);
//        ResponseEntity<CancelElectionResponse> result = electionController.cancelElection(request);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    void addCandidateToElection() {
//        AddCandidateToElectionRequest request = new AddCandidateToElectionRequest();
//        request.setElectionId(1L);
//
//        AddCandidateToElectionResponse response = new AddCandidateToElectionResponse();
//        response.setMessage("Candidate added successfully");
//
//        when(electionService.addCandidateToElection(request)).thenReturn(response);
//        ResponseEntity<AddCandidateToElectionResponse> result = electionController.addCandidateToElection(request);
//        assertEquals(HttpStatus.CREATED, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
}