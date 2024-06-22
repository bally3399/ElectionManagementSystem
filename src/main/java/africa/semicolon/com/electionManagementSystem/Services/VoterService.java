package africa.semicolon.com.electionManagementSystem.Services;

import africa.semicolon.com.electionManagementSystem.dto.request.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.request.GetAllVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.request.GetVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.response.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dto.response.GetAllVoteResponse;
import africa.semicolon.com.electionManagementSystem.dto.response.GetVoteResponse;
import africa.semicolon.com.electionManagementSystem.exception.VoterNotFoundException;
import africa.semicolon.com.electionManagementSystem.models.Vote;
import africa.semicolon.com.electionManagementSystem.repository.VoteRepository;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoterService implements VoteServicesImpl{

    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private ModelMapper modelMapper;

    @Override
    public AddVoteResponse addVote(AddVoteRequest voteRequest) {
        var voteToBeAdded = voterRepository.findById(voteRequest.getId());
        if(voteToBeAdded.isEmpty()){
            throw new VoterNotFoundException("No voter found");
        }
        Vote vote = modelMapper.map(voteRequest, Vote.class);
        vote.setVoteId(voteRequest.getId());
        vote.setCategory(voteRequest.getCategory());
        vote = voteRepository.save(vote);
        return modelMapper.map(vote, AddVoteResponse.class);
    }
    @Override
    public GetVoteResponse getVote(GetVoteRequest voteRequest) {
        Vote vote = new Vote();
        var voteToGet = voteRepository.findById(voteRequest.getId());
        if(voteToGet.isEmpty()){
            throw new VoterNotFoundException("No voter found");
        }
        vote.setVoteId(voteRequest.getId());
        GetVoteResponse getVoteResponse = new GetVoteResponse();
        getVoteResponse.setId(voteRequest.getId());
        voteRepository.save(vote);
        return getVoteResponse;
    }
    @Override
    public GetAllVoteResponse getAllVote(GetAllVoteRequest voteRequest) {
        Vote vote = new Vote();
        var getAllVote = voteRepository.findVoteByCategory(voteRequest.getCategory());
        if(getAllVote == null){
            throw new VoterNotFoundException("No vote found");
        }
        vote.setCategory(voteRequest.getCategory());
        GetAllVoteResponse getAllVoteResponse = new GetAllVoteResponse();
        voteRepository.save(vote);
        return getAllVoteResponse;
    }
}
