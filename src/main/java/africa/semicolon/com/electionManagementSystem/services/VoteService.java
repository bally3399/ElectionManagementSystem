package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetAllVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetAllVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetVoteResponse;

public interface VoteService {
    GetVoteResponse getVote(GetVoteRequest voteRequest);
    GetAllVoteResponse getAllVote(GetAllVoteRequest voteRequest);
    AddVoteResponse addVote(AddVoteRequest voteRequest);
}
