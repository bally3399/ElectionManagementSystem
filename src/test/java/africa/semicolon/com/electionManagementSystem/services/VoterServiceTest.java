package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AlreadyVotedForCandidateException;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CastBallotRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CastBallotResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.exceptions.UnderAgeVoterException;
import africa.semicolon.com.electionManagementSystem.exceptions.VoterAlreadyExistException;
import africa.semicolon.com.electionManagementSystem.models.Address;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VoterServiceTest {
    @Autowired
    private VoterServiceImplementation voterService;
    @Autowired
    private VoterRepository voterRepository;

    @Test
    public void registerVoterTest() {
        RegisterVoterRequest registerVoterRequest = new RegisterVoterRequest();
        registerVoterRequest.setFirstName("newfirstname");
        registerVoterRequest.setLastName("newlastname");
        registerVoterRequest.setAdminId(100L);
        Address address = new Address();
        address.setBuildingNumber("00123");
        address.setWard("newWardName");
        address.setLocalGovernmentArea("newLocalGovtArea");
        address.setCity("newCityName");
        address.setState("newStateName");
        registerVoterRequest.setAddress(address);
        registerVoterRequest.setNationalIdentificationNumber("1234567890");
        registerVoterRequest.setPhoneNumber("001234567");
        registerVoterRequest.setEmail("newvoter@gmail.com");
        registerVoterRequest.setDateOfBirth("21/10/1990");
        RegisterVoterResponse registerVoterResponse = voterService.register(registerVoterRequest);
        assertNotNull(registerVoterResponse);
        assertTrue(registerVoterResponse.getMessage().contains("Voter Registered Successfully"));
        //Assertions.assertEquals(2,voterRepository.findAll().size());
    }



    @Test
    public void sameVoterCannotBeRegisteredMoreThanOnce() {
        try {
        RegisterVoterRequest registerVoterRequest = new RegisterVoterRequest();
        registerVoterRequest.setFirstName("first name");
        registerVoterRequest.setLastName("last name");
        registerVoterRequest.setAdminId(100L);
        Address address = new Address();
        address.setBuildingNumber("123");
        address.setWard("Ward Name");
        address.setLocalGovernmentArea("Local Govt Area");
        address.setCity("City Name");
        address.setState("State Name");
        registerVoterRequest.setAddress(address);
        registerVoterRequest.setPhoneNumber("1234567");
        registerVoterRequest.setEmail("voter@gmail.com");
        registerVoterRequest.setDateOfBirth("21/10/2000");
        RegisterVoterResponse registerVoterResponse = voterService.register(registerVoterRequest);
        assertNotNull(registerVoterResponse);
        assertTrue(registerVoterResponse.getMessage().contains("Voter Registered Successfully"));
        }
        catch (VoterAlreadyExistException e){
            assertThat(e.getMessage()).isEqualTo("Voter already exist");
        }
    }

    @Test
    public void underAgeVoterCannotBeRegistered_ThrowsUnderAgeUserException() {
        try {
            RegisterVoterRequest registerVoterRequest = new RegisterVoterRequest();
            registerVoterRequest.setFirstName("second");
            registerVoterRequest.setLastName("name");
            registerVoterRequest.setAdminId(100L);
            Address address = new Address();
            address.setBuildingNumber("456");
            address.setWard("WardName2");
            address.setLocalGovernmentArea("LGovtArea");
            address.setCity("CityName2");
            address.setState("StateName2");
            registerVoterRequest.setAddress(address);
            registerVoterRequest.setPhoneNumber("9876543210");
            registerVoterRequest.setNationalIdentificationNumber("234567890");
            registerVoterRequest.setEmail("another@example.com");
            registerVoterRequest.setDateOfBirth("21/10/2022");
            voterService.register(registerVoterRequest);
        } catch (UnderAgeVoterException e) {
            assertThat(e.getMessage()).isEqualTo("Under age voter not eligible for registration");
        }
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void voterCanCastBallotTest() {
        CastBallotRequest castBallotRequest = new CastBallotRequest();
        castBallotRequest.setVoterId(204L);
        castBallotRequest.setCandidateId(402L);
        CastBallotResponse castBallotResponse = voterService.castBallot(castBallotRequest);
        voterService.castBallot(castBallotRequest);

        assertThat(castBallotResponse).isNotNull();

    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void voterCanCastBallotTwiceTest() {
        CastBallotRequest castBallotRequest = new CastBallotRequest();
        castBallotRequest.setVoterId(202L);
        castBallotRequest.setCandidateId(402L);

        assertThrows(AlreadyVotedForCandidateException.class, ()->voterService.castBallot(castBallotRequest));

    }



}
