package africa.semicolon.com.electionManagementSystem.controller;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddCandidateToElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CancelElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddCandidateToElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.services.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ElectionController {

    private final ElectionService electionService;

    @PostMapping("/scheduleElection")
    public ResponseEntity<ScheduleElectionResponse> scheduleElection(@RequestBody @Valid ScheduleElectionRequest scheduleElectionRequest) {
        ScheduleElectionResponse response = electionService.scheduleElection(scheduleElectionRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelElection")
    public ResponseEntity<CancelElectionResponse> cancelElection(@RequestBody @Valid CancelElectionRequest cancelElectionRequest) {
        CancelElectionResponse response = electionService.cancelElection(cancelElectionRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addCandidateToElection")
    public ResponseEntity<AddCandidateToElectionResponse> addCandidateToElection(@RequestBody @Valid AddCandidateToElectionRequest addCandidateToElectionRequest) {
        AddCandidateToElectionResponse response = electionService.addCandidateToElection(addCandidateToElectionRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}