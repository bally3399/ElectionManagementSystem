package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.CastBallotRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterInformationRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CastBallotResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ViewVoterInformationResponse;
import africa.semicolon.com.electionManagementSystem.models.Voter;

public interface VoterService {
    RegisterVoterResponse register(RegisterVoterRequest registerVoterRequest);
    Voter viewVoter(ViewVoterRequest viewRequest);

    Voter findVoterById(Long id);

    CastBallotResponse castBallot(CastBallotRequest castBallotRequest);
    Voter getVoterById(Long id);

    ViewVoterInformationResponse viewInfo(ViewVoterInformationRequest voterInfo);
    Voter getVoterByEmailAndPassword(Object email, String password);


}
