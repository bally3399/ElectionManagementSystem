package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.models.Voter;

public interface VoterService {
    RegisterVoterResponse register(RegisterVoterRequest registerVoterRequest);


    Voter viewVoter(ViewVoterRequest viewRequest);
}
