package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CancelVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoteCountRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelVoteResponse;

public interface VoteService {
    AddVoteResponse addVote(AddVoteRequest addVoteRequest);
    Long viewVoteCount(ViewVoteCountRequest viewVoteCountRequest);
    CancelVoteResponse cancelVote(CancelVoteRequest cancelVoteRequest);


}

