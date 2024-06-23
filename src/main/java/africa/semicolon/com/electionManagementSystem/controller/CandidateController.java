package africa.semicolon.com.electionManagementSystem.controller;

import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RemoveCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
import africa.semicolon.com.electionManagementSystem.models.Candidate;
import africa.semicolon.com.electionManagementSystem.services.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/register")
    public ResponseEntity<RegisterCandidateResponse> registerCandidate(@RequestBody RegisterCandidateRequest request) {
        RegisterCandidateResponse response = candidateService.registerCandidate(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable long id) {
        Candidate candidate = candidateService.findCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.findAllCandidates();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<RemoveCandidateResponse> removeCandidate(@RequestBody RemoveCandidateRequest request) {
        RemoveCandidateResponse response = candidateService.removeCandidate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateCandidateResponse> updateCandidate(@RequestBody UpdateCandidateRequest request) {
        UpdateCandidateResponse response = candidateService.updateCandidate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getNoOfCandidates() {
        long count = candidateService.getNoOfCandidates();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
