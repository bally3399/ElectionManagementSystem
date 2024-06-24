package africa.semicolon.com.electionManagementSystem.controller;

import africa.semicolon.com.electionManagementSystem.dtos.requests.*;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
import africa.semicolon.com.electionManagementSystem.services.ElectionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/election")
@AllArgsConstructor
public class ElectionController {

    private final ElectionService electionService;

    @PostMapping("/scheduleElection")
    public ResponseEntity<ScheduleElectionResponse> scheduleElection(@RequestBody ScheduleElectionRequest scheduleElectionRequest) {
        ScheduleElectionResponse response = electionService.scheduleElection(scheduleElectionRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/updateElectionStatus")
    public ResponseEntity<UpdateElectionStatusResponse> updateElectionStatus(@RequestBody UpdateElectionStatusRequest updateElectionStatusRequest) {
        UpdateElectionStatusResponse response = electionService.updateElectionStatus(updateElectionStatusRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateElection")
    public ResponseEntity<UpdateElectionResponse> updateElection(@RequestBody UpdateElectionRequest updateElectionRequest) {
        UpdateElectionResponse response = electionService.updateElection(updateElectionRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findElection")
    public ResponseEntity<FindElectionResponse> findElection(@RequestBody FindElectionRequest findElectionRequest) {
        FindElectionResponse response = electionService.findElection(findElectionRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}