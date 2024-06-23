package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RemoveCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
import africa.semicolon.com.electionManagementSystem.models.Candidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static africa.semicolon.com.electionManagementSystem.models.Party.APC;
import static africa.semicolon.com.electionManagementSystem.models.Party.PDP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class CandidateServiceTest {

    @Autowired
    private CandidateService candidateService;

    @Test
    public void registerCandidateTest()  {
        RegisterCandidateRequest candidateRequest = new RegisterCandidateRequest();
        candidateRequest.setFirstName("King");
        candidateRequest.setLastName("Jumong");
        candidateRequest.setEmail("lawaltoheeb36@gmail.com");
        candidateRequest.setDateOfBirth(LocalDate.of(1985, 12, 29));
        candidateRequest.setAdminId(100L);
        candidateRequest.setBiography("Biography");
        candidateRequest.setPhoneNumber("08155336155");
        candidateRequest.setParty(APC);
        candidateRequest.setPositionContested("Presidential");
        var response = candidateService.registerCandidate(candidateRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("candidate registration successful");

    }

    @Test
    public void testThatCandidateServiceThrowExceptionIfCandidateIsBelow35(){
        RegisterCandidateRequest candidateRequest = new RegisterCandidateRequest();
        candidateRequest.setFirstName("King");
        candidateRequest.setLastName("Jumong");
        candidateRequest.setEmail("jumong@gmail.com");
        candidateRequest.setDateOfBirth(LocalDate.of(2012, 12, 29));
        candidateRequest.setBiography("Biography");
        candidateRequest.setPhoneNumber("08155336155");
        candidateRequest.setAdminId(100L);
        candidateRequest.setParty(APC);
        candidateRequest.setPositionContested("Presidential");
        try{
            var response = candidateService.registerCandidate(candidateRequest);
            assertThat(response).isNull();
        } catch (Exception e){
            assertThat(e).isNotNull();
            assertThat(e.getMessage()).isEqualTo("candidate must be at least 35 years old");
        }
    }

    @Test
    public void testThatCandidateCannotRegisterTwice(){
        RegisterCandidateRequest candidateRequest = new RegisterCandidateRequest();
        candidateRequest.setFirstName("King");
        candidateRequest.setLastName("Jumong");
        candidateRequest.setEmail("adebabalola@gmail.com");
        candidateRequest.setDateOfBirth(LocalDate.of(1985, 12, 29));
        candidateRequest.setBiography("Biography");
        candidateRequest.setPhoneNumber("08155336155");
        candidateRequest.setAdminId(100L);
        candidateRequest.setParty(APC);
        candidateRequest.setPositionContested("Presidential");
        try{
            RegisterCandidateResponse response = candidateService.registerCandidate(candidateRequest);
            assertThat(response).isNotNull();
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("candidate with same email already exists");
        }
    }

    @Test
    public void testFindCandidate(){
        Candidate candidate = candidateService.findCandidateById(400L);
        assertThat(candidate).isNotNull();
        assertThat(candidate.getFirstName()).isEqualTo("Ade");
    }

    @Test
    public void testThatCandidateServiceCanFindAllCandidates(){
        List<Candidate> candidates = candidateService.findAllCandidates();
        assertThat(candidates.size()).isEqualTo(2L);
    }

    @Test
    public void testUpdateCandidate(){
        Candidate candidate = candidateService.findCandidateById(401L);
        assertThat(candidate.getParty()).isEqualTo(APC);
        assertThat(candidate.getPositionContested()).isEqualTo("Governor");
        UpdateCandidateRequest updateCandidateRequest = new UpdateCandidateRequest();
        updateCandidateRequest.setCandidateId(401L);
        updateCandidateRequest.setParty(PDP);
        updateCandidateRequest.setPositionContested("President");
        UpdateCandidateResponse response = candidateService.updateCandidate(updateCandidateRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("candidate updated successfully");
        assertThat(candidateService.findCandidateById(401L).getParty()).isEqualTo(PDP);
        assertThat(candidate.getId()).isEqualTo(401L);
        assertThat(candidateService.findCandidateById(401L).getPositionContested()).isEqualTo("President");
    }

    @Test
    public void testRemoveCandidate(){
        assertThat(candidateService.getNoOfCandidates()).isEqualTo(2);
        RemoveCandidateRequest removeCandidateRequest = new RemoveCandidateRequest();
        removeCandidateRequest.setId(400L);
        RemoveCandidateResponse response = candidateService.removeCandidate(removeCandidateRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("candidate removed successfully");
        assertThat(candidateService.getNoOfCandidates()).isEqualTo(1);
    }


}