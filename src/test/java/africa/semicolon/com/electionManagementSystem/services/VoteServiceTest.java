package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CastBallotRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetAllVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.GetVoteRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CastVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetAllVoteResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.GetVoteResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.UserNotFoundException;
import africa.semicolon.com.electionManagementSystem.models.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/db/data.sql")
public class VoteServiceTest {

    @Autowired
    private VoteServiceImplementation voteServices;
//    @Test
//    public void testAddVote()throws UserNotFoundException {
//        AddVoteRequest request = new AddVoteRequest();
//        request.setId(1L);
//        request.setCandidateId(400L);
//        AddVoteResponse response =  voteServices.addVote(request);
//        assertThat(response).isNotNull();
//
//    }
//
//

    @Test
    public void testCastVote(){
        CastBallotRequest castBallotRequest = new CastBallotRequest();
        castBallotRequest.setCandidateId(400L);
        castBallotRequest.setVoterId(200L);
        CastVoteResponse response = voteServices.caseVote(castBallotRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("vote cast successfully");
    }

    @Test
    public void testGetNumberOfVote(){
        assertThat(voteServices.getNumberOfVote()).isEqualTo(2L);
    }

    @Test
    public void testThatOneVoterCannotVoteTwice(){
        CastBallotRequest castBallotRequest = new CastBallotRequest();
        castBallotRequest.setCandidateId(400L);
        castBallotRequest.setVoterId(200L);
        try{
            CastVoteResponse response = voteServices.caseVote(castBallotRequest);
            assertThat(response).isNotNull();
        } catch (Exception e){
            assertThat(e.getMessage()).isNotNull();
            assertThat(e.getMessage()).isEqualTo("Voter cast vote already");
        }
    }

    @Test
    public void testThatVoteServiceThrowsExceptionIfWrongVoterIdIsEntered(){
        CastBallotRequest castBallotRequest = new CastBallotRequest();
        castBallotRequest.setCandidateId(400L);
        castBallotRequest.setVoterId(1200L);
        try{
            CastVoteResponse response = voteServices.caseVote(castBallotRequest);
            assertThat(response).isNotNull();
        } catch (Exception e){
            assertThat(e.getMessage()).isNotNull();
            assertThat(e.getMessage()).isEqualTo("Voter does not exist");
        }
    }

    @Test
    public void testThatVoteServiceThrowsExceptionIfWrongCandidateIdIsEntered(){
        CastBallotRequest castBallotRequest = new CastBallotRequest();
        castBallotRequest.setCandidateId(1400L);
        castBallotRequest.setVoterId(200L);
        try{
            CastVoteResponse response = voteServices.caseVote(castBallotRequest);
            assertThat(response).isNotNull();
        } catch (Exception e){
            assertThat(e.getMessage()).isNotNull();
            assertThat(e.getMessage()).isEqualTo("candidate not found");
        }
    }

    @Test
    public void testGetVote(){
        Optional<Vote> response =  voteServices.getVote(500L);
        assertThat(response).isNotNull();
    }



    }

