package africa.semicolon.com.electionManagementSystem.controller;


import africa.semicolon.com.electionManagementSystem.dtos.requests.*;

import africa.semicolon.com.electionManagementSystem.dtos.responses.AddCandidateToElectionResponse;

import africa.semicolon.com.electionManagementSystem.dtos.responses.FindElectionResponse;
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

import java.time.LocalTime;

import static africa.semicolon.com.electionManagementSystem.models.Category.NATIONAL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        scheduleElectionRequest.setStartTime("07:00");
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

        mockMvc.perform(patch("/api/v1/election/updateElectionStatus")
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


        mockMvc.perform(patch("/api/v1/election/updateElection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(updateElectionRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testFindElection() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FindElectionRequest findElectionRequest = new FindElectionRequest();
        findElectionRequest.setElectionId(302L);

        mockMvc.perform(get("/api/v1/election/findElection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(findElectionRequest))
        ).andExpect(status().isOk()).andDo(print());
    }

}