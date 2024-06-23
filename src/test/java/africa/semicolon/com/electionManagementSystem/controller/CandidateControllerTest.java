//package africa.semicolon.com.electionManagementSystem.controller;
//
//import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.RemoveCandidateRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateCandidateRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateResponse;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
//import africa.semicolon.com.electionManagementSystem.models.Candidate;
//import africa.semicolon.com.electionManagementSystem.services.CandidateService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CandidateControllerTest {
//
//    @Mock
//    private CandidateService candidateService;
//
//    @InjectMocks
//    private CandidateController candidateController;
//
//    @Test
//    void registerCandidateTest() {
//        RegisterCandidateRequest request = new RegisterCandidateRequest();
//        RegisterCandidateResponse response = new RegisterCandidateResponse();
//
//        when(candidateService.registerCandidate(request)).thenReturn(response);
//
//        ResponseEntity<RegisterCandidateResponse> result = candidateController.registerCandidate(request);
//        assertEquals(HttpStatus.CREATED, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    void getCandidateByIdTest() {
//        long id = 1L;
//        Candidate candidate = new Candidate();
//
//        when(candidateService.findCandidateById(id)).thenReturn(candidate);
//
//        ResponseEntity<Candidate> result = candidateController.getCandidateById(id);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(candidate, result.getBody());
//    }
//
//    @Test
//    void getAllCandidatesTest() {
//        List<Candidate> candidates = List.of(new Candidate());
//
//        when(candidateService.findAllCandidates()).thenReturn(candidates);
//
//        ResponseEntity<List<Candidate>> result = candidateController.getAllCandidates();
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(candidates, result.getBody());
//    }
//
//    @Test
//    void removeCandidateTest() {
//        RemoveCandidateRequest request = new RemoveCandidateRequest();
//        RemoveCandidateResponse response = new RemoveCandidateResponse();
//
//        when(candidateService.removeCandidate(request)).thenReturn(response);
//
//        ResponseEntity<RemoveCandidateResponse> result = candidateController.removeCandidate(request);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    void updateCandidateTest() {
//        UpdateCandidateRequest request = new UpdateCandidateRequest();
//        UpdateCandidateResponse response = new UpdateCandidateResponse();
//
//        when(candidateService.updateCandidate(request)).thenReturn(response);
//
//        ResponseEntity<UpdateCandidateResponse> result = candidateController.updateCandidate(request);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    void getNoOfCandidatesTest() {
//        long count = 10L;
//
//        when(candidateService.getNoOfCandidates()).thenReturn(count);
//
//        ResponseEntity<Long> result = candidateController.getNoOfCandidates();
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(count, result.getBody());
//    }
//}