package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetAllVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetAllVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetVoteResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.VoterNotFoundException;
import africa.semicolon.com.electionManagementSystem.models.Vote;
import africa.semicolon.com.electionManagementSystem.repository.VoteRepository;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteServiceImplementation implements VoteService {

    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private ModelMapper modelMapper;

    @Override
    public GetVoteResponse getVote(GetVoteRequest voteRequest) {
        Vote vote = new Vote();
        var voteToGet = voteRepository.findById(voteRequest.getId());
        vote.setVoteId(voteRequest.getId());
        GetVoteResponse getVoteResponse = new GetVoteResponse();
        getVoteResponse.setId(voteRequest.getId());
        voteRepository.save(vote);
        return getVoteResponse;
    }

    @Override
    public GetAllVoteResponse getAllVote(GetAllVoteRequest voteRequest) {
        Vote vote = new Vote();
        //var getAllVote = voteRepository.findVoteByCandidate(voteRequest.getCandidateId());
        GetAllVoteResponse getAllVoteResponse = new GetAllVoteResponse();
        voteRepository.save(vote);
        return getAllVoteResponse;
    }

    @Override
    public AddVoteResponse addVote(AddVoteRequest voteRequest) {
        var voteToBeAdded = voterRepository.findById(voteRequest.getId());
        if (voteToBeAdded.isEmpty()) {
            throw new VoterNotFoundException("No voter found");
        }
        Vote vote = modelMapper.map(voteRequest, Vote.class);
        vote.setVoteId(voteRequest.getId());
        vote = voteRepository.save(vote);
        return modelMapper.map(vote, AddVoteResponse.class);
    }


}
