package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.models.Category;
import africa.semicolon.com.electionManagementSystem.models.Party;
import africa.semicolon.com.electionManagementSystem.repository.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VoteServiceTest {
    @Autowired
    private  VoteRepository voteRepository;
    @Autowired
    private VoteService voteService;

    @Test
    public void addVote() {
        AddVoteRequest addVoteRequest = new AddVoteRequest();
        addVoteRequest.setVoterId("leo");
        addVoteRequest.setCategory(Category.NATIONAL);
        addVoteRequest.setParty(Party.APC);
        addVoteRequest.setElectionId(1233);
        AddVoteResponse addVoteResponse = voteService.addVote(addVoteRequest);
        assertNotNull(addVoteResponse);
        assertTrue(addVoteResponse.getMessage().contains("Vote Added successfully"));
        Assertions.assertEquals(1,voteRepository.findAll().size());


    }

}