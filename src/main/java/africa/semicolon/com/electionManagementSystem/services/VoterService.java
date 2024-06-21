package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.reponses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;

public interface VoterService {
    RegisterVoterResponse register(RegisterVoterRequest registerVoterRequest);
}
