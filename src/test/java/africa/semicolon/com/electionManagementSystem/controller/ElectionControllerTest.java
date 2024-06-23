//package africa.semicolon.com.electionManagementSystem.controller;
//
//
//import africa.semicolon.com.electionManagementSystem.dtos.requests.AddCandidateToElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.CancelElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.AddCandidateToElectionResponse;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelElectionResponse;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
//import africa.semicolon.com.electionManagementSystem.services.ElectionService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ElectionControllerTest {
//
//    @InjectMocks
//    private ElectionController electionController;
//
//    @Mock
//    private ElectionService electionService;
//
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
//}