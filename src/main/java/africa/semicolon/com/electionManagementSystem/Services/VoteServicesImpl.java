package africa.semicolon.com.electionManagementSystem.Services;

import africa.semicolon.com.electionManagementSystem.dto.request.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.request.GetAllVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.request.GetVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.response.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dto.response.GetAllVoteResponse;
import africa.semicolon.com.electionManagementSystem.dto.response.GetVoteResponse;

public interface VoteServicesImpl {
    AddVoteResponse addVote(AddVoteRequest voteRequest);
    GetVoteResponse getVote(GetVoteRequest voteRequest);
    GetAllVoteResponse getAllVote(GetAllVoteRequest voteRequest);
}
