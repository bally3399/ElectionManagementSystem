package africa.semicolon.com.electionManagementSystem.voteService;

import africa.semicolon.com.electionManagementSystem.Services.VoteServicesImpl;
import africa.semicolon.com.electionManagementSystem.dto.request.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.request.GetAllVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.request.GetVoteRequest;
import africa.semicolon.com.electionManagementSystem.dto.response.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dto.response.GetAllVoteResponse;
import africa.semicolon.com.electionManagementSystem.dto.response.GetVoteResponse;
import africa.semicolon.com.electionManagementSystem.exception.UserNotFoundException;
import africa.semicolon.com.electionManagementSystem.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VoteServiceTest {

    @Autowired
    private VoteServicesImpl voteServices;
    @Test
    public void testAddVote()throws UserNotFoundException {
        AddVoteRequest request = new AddVoteRequest();
       request.setId(1L);
       request.setCategory(Category.NATIONAL);
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
        request.setCategory(Category.NATIONAL);
        GetAllVoteResponse response = voteServices.getAllVote(request);
        assertThat(response).isNotNull();


    }

}
