package africa.semicolon.com.electionManagementSystem.controller;

import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.InValidVoterException;
import africa.semicolon.com.electionManagementSystem.exceptions.UnderAgeVoterException;
import africa.semicolon.com.electionManagementSystem.models.Voter;
import africa.semicolon.com.electionManagementSystem.services.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voters")
public class VoterController {
    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterVoterResponse> registerVoter(@RequestBody RegisterVoterRequest registerVoterRequest) {
        try {
            RegisterVoterResponse response = voterService.register(registerVoterRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UnderAgeVoterException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
//        Voter voter = voterService.getVoterByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
//        if (voter == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        LoginResponse loginResponse = new LoginResponse();
//        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
//    }

    @PostMapping("/view")
    public ResponseEntity<Voter> viewVoter(@RequestBody ViewVoterRequest viewVoterRequest) {
        try {
            Voter voter = voterService.viewVoter(viewVoterRequest);
            return new ResponseEntity<>(voter, HttpStatus.OK);
        } catch (InValidVoterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}