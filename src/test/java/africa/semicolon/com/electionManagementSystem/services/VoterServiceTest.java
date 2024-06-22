package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterInformationRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.exceptions.UnderAgeVoterException;
import africa.semicolon.com.electionManagementSystem.exceptions.VoterAlreadyExistException;
import africa.semicolon.com.electionManagementSystem.models.Address;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDate;

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
        Address address = new Address();
        address.setBuildingNumber("00123");
        address.setWard("newWardName");
        address.setLocalGovernmentArea("newLocalGovtArea");
        address.setCity("newCityName");
        address.setState("newStateName");
        registerVoterRequest.setAddress(address);
        registerVoterRequest.setPhoneNumber("001234567");
        registerVoterRequest.setEmail("newvoter@gmail.com");
        registerVoterRequest.setDateOfBirth("21/10/1990");
        RegisterVoterResponse registerVoterResponse = voterService.register(registerVoterRequest);
        assertNotNull(registerVoterResponse);
        assertTrue(registerVoterResponse.getMessage().contains("Voter Registered Successfully"));
        Assertions.assertEquals(2,voterRepository.findAll().size());
    }



    @Test
    public void sameVoterCannotBeRegisteredMoreThanOnce() {
        try {
        RegisterVoterRequest registerVoterRequest = new RegisterVoterRequest();
        registerVoterRequest.setFirstName("first name");
        registerVoterRequest.setLastName("last name");
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
            Address address = new Address();
            address.setBuildingNumber("456");
            address.setWard("WardName2");
            address.setLocalGovernmentArea("LGovtArea");
            address.setCity("CityName2");
            address.setState("StateName2");
            registerVoterRequest.setAddress(address);
            registerVoterRequest.setPhoneNumber("9876543210");
            registerVoterRequest.setEmail("another@example.com");
            registerVoterRequest.setDateOfBirth("21/10/2022");
            voterService.register(registerVoterRequest);
        } catch (UnderAgeVoterException e) {
            assertThat(e.getMessage()).isEqualTo("Under age voter not eligible for registration");
        }
    }

    @Test
    public void testToViewVoterInformation(){
        ViewVoterInformationRequest voterInfo = new ViewVoterInformationRequest();
        voterInfo.setVoterNumber("100000");
        voterInfo.setPhoneNumber("9876543210");
        voterInfo.setEmail("another@example.com");
        voterInfo.setLastName("name");

    }



}
