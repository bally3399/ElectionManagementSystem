package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;

public interface VoterService {
    LoginResponse login(LoginRequest loginRequest);
}
