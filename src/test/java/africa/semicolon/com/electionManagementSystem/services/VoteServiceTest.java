package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetAllVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetAllVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetVoteResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VoteServiceTest {

    @Autowired
    private VoteServiceImplementation voteServices;
    @Test
    public void testAddVote()throws UserNotFoundException {
        AddVoteRequest request = new AddVoteRequest();
        request.setId(1L);
        request.setCandidateId(400L);
        AddVoteResponse response =  voteServices.addVote(request);
        assertThat(response).isNotNull();


    }
    @Test
    public  void  testGetVote(){
        GetVoteRequest request = new GetVoteRequest();
        request.setId(1L);
        GetVoteResponse response =  voteServices.getVote(request);
        assertThat(response).isNotNull();


    }
    @Test
    public void  testGetAllVote(){
        GetAllVoteRequest request = new GetAllVoteRequest();
        request.setCandidateId(400L);
        GetAllVoteResponse response = voteServices.getAllVote(request);
        assertThat(response).isNotNull();


    }

}

