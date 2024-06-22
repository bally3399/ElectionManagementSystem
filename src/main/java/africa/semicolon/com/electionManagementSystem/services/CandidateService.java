package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RemoveCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
import africa.semicolon.com.electionManagementSystem.models.Candidate;

import java.util.List;

public interface CandidateService {
    RegisterCandidateResponse registerCandidate(RegisterCandidateRequest candidateRequest);
    long getNoOfCandidates();
    Candidate findCandidateById(long id);
    List<Candidate> findAllCandidates();
    RemoveCandidateResponse removeCandidate(RemoveCandidateRequest removeCandidateRequest);
    UpdateCandidateResponse updateCandidate(UpdateCandidateRequest updateCandidateRequest);
}
