package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CancelVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoteCountRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelVoteResponse;
import africa.semicolon.com.electionManagementSystem.models.Vote;
import africa.semicolon.com.electionManagementSystem.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VoteServiceImplementation implements VoteService{
    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddVoteResponse addVote(AddVoteRequest addVoteRequest) {
        Vote vote = modelMapper.map(addVoteRequest,Vote.class);
        voteRepository.save(vote);
        var response = modelMapper.map(vote, AddVoteResponse.class);
        response.setMessage("Vote Added successfully");
        return response;
    }

    @Override
    public Long viewVoteCount(ViewVoteCountRequest viewVoteCountRequest) {
        Vote vote = voteRepository.findAll(viewVoteCountRequest.getElectionId());
        return 0L;
    }

    @Override
    public CancelVoteResponse cancelVote(CancelVoteRequest cancelVoteRequest) {
        Vote vote = modelMapper.map(cancelVoteRequest,Vote.class);
        voteRepository.delete(vote);
        CancelVoteResponse response = modelMapper.map(vote, CancelVoteResponse.class);
        response.setMessage("vote canceled successfully");
        return response;
    }
}
