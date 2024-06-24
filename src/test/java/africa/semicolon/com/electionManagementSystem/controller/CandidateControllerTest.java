package africa.semicolon.com.electionManagementSystem.controller;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RemoveCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
import africa.semicolon.com.electionManagementSystem.models.Candidate;
import africa.semicolon.com.electionManagementSystem.models.Party;
import africa.semicolon.com.electionManagementSystem.models.PositionContested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;


@SpringBootTest
@Sql(scripts ={"/db/data.sql"})
public class CandidateControllerTest {

    @Autowired
    private CandidateController candidateController;

    @Test
    public void testRegisterCandidate() {
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setId(100L);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setParty(Party.APC);
        request.setDateOfBirth(LocalDate.of(1980, 1, 1));
        request.setBiography("John Doe is a candidate for the upcoming election.");
        request.setPhoneNumber("1234567890");
        request.setEmail("sulaimabaliqis@gmail.com");
        request.setPositionContested(PositionContested.PRESIDENT);
        request.setElectionId(301L);

        ResponseEntity<RegisterCandidateResponse> response = candidateController.registerCandidate(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCandidateById() {
        ResponseEntity<Candidate> response = candidateController.getCandidateById(400);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetAllCandidates() {
        ResponseEntity<List<Candidate>> response = candidateController.getAllCandidates();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void testRemoveCandidate() {
        RemoveCandidateRequest request = new RemoveCandidateRequest();
        request.setAdminId(100L);
        request.setId(400L);

        ResponseEntity<RemoveCandidateResponse> response = candidateController.removeCandidate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateCandidate() {
        UpdateCandidateRequest request = new UpdateCandidateRequest();
        request.setAdminId(100L);
        request.setCandidateId(400L);
        request.setFirstName("Jane");
        request.setLastName("Doe");
        request.setParty(Party.APC);
        request.setDateOfBirth(LocalDate.of(1985, 5, 15));
        request.setBiography("Jane Doe is a candidate for the upcoming election.");
        request.setPhoneNumber("0987654321");
        request.setEmail("janedoe@example.com");
        request.setPositionContested(PositionContested.PRESIDENT);


        ResponseEntity<UpdateCandidateResponse> response = candidateController.updateCandidate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetNoOfCandidates() {
        ResponseEntity<Long> response = candidateController.getNoOfCandidates();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3L, Objects.requireNonNull(response.getBody()).longValue());
    }
}