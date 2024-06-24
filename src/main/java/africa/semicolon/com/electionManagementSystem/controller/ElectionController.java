//package africa.semicolon.com.electionManagementSystem.controller;
//
//import africa.semicolon.com.electionManagementSystem.dtos.requests.AddCandidateToElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateElectionStatusRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
//import africa.semicolon.com.electionManagementSystem.services.ElectionService;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import static org.springframework.http.HttpStatus.CREATED;
//
//@RestController
//@RequestMapping("/api/v1/election")
//@AllArgsConstructor
//public class ElectionController {
//
//    private final ElectionService electionService;
//
//    @PostMapping("/scheduleElection")
//    public ResponseEntity<ScheduleElectionResponse> scheduleElection(@RequestBody ScheduleElectionRequest scheduleElectionRequest) {
//        ScheduleElectionResponse response = electionService.scheduleElection(scheduleElectionRequest);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    @PatchMapping("/updateElectionStatus")
//    public ResponseEntity<UpdateElectionStatusResponse> updateElectionStatus(@RequestBody UpdateElectionStatusRequest updateElectionStatusRequest) {
//        UpdateElectionStatusResponse response = electionService.updateElectionStatus(updateElectionStatusRequest);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PatchMapping("/updateElectionStatus")
//    public ResponseEntity<UpdateElectionResponse> updateElection(@RequestBody UpdateElectionRequest updateElectionRequest) {
//        UpdateElectionResponse response = electionService.updateElection(updateElectionRequest);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping("/findElection")
//    public ResponseEntity<?> findElection(@RequestBody FindElectionRequest findElectionRequest) {
//        FindElectionResponse findElectionResponse = electionService.findElection(findElectionRequest);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }



//    @DeleteMapping("/cancelElection")
//    public ResponseEntity<CancelElectionResponse> cancelElection(@RequestBody @Valid CancelElectionRequest cancelElectionRequest) {
//        CancelElectionResponse response = electionService.cancelElection(cancelElectionRequest);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @PostMapping("/addCandidateToElection")
//    public ResponseEntity<AddCandidateToElectionResponse> addCandidateToElection(@RequestBody @Valid AddCandidateToElectionRequest addCandidateToElectionRequest) {
//        AddCandidateToElectionResponse response = electionService.addCandidateToElection(addCandidateToElectionRequest);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//}