package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.CastBallotRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CastVoteResponse;
import africa.semicolon.com.electionManagementSystem.models.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteService {
//    List<Vote> getAllVote();
//    AddVoteResponse addVote(AddVoteRequest voteRequest);

    CastVoteResponse caseVote(CastBallotRequest voteRequest);

    Long getNumberOfVote();

    Optional<Vote> getVote(long id);
}
