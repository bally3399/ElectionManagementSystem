package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.exceptions.UnderAgeVoterException;
import africa.semicolon.com.electionManagementSystem.exceptions.VoterAlreadyExistException;
import africa.semicolon.com.electionManagementSystem.models.Address;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts ={"/db/data.sql"})
public class VoterServiceTest {
    @Autowired
    private VoterServiceImplementation voterService;
    @Autowired
    private VoterRepository voterRepository;

    @Test
    public void registerVoterTest() {
        RegisterVoterRequest registerVoterRequest = getRegisterVoterRequest();
//        voterService.register(getRegisterVoterRequest());

        RegisterVoterResponse registerVoterResponse = voterService.register(getRegisterVoterRequest());

        assertNotNull(registerVoterResponse);
        assertTrue(registerVoterResponse.getMessage().contains("Voter Registered Successfully"));
        assertEquals(1,voterRepository.findAll().size());
    }

    private static RegisterVoterRequest getRegisterVoterRequest() {
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
//        LocalDate dateOfBirth = LocalDate.of(1995, 10, 20);
        registerVoterRequest.setDateOfBirth("21/10/2022");
        return registerVoterRequest;
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

//    @Test
//    public void testToLogin() {
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setPassword("1245");
//        loginRequest.setUsername("chichi");
//        LoginResponse response = voterService.login(loginRequest);
//
//        assertNotNull(response);
//        assertTrue(response.getMessage().contains("success"));
//
//    }

    @Test
    public void testToViewVoterHistory() {

        RegisterVoterRequest registerVoterRequest = getRegisterVoterRequest();
        voterService.register(getRegisterVoterRequest());

       ViewVoterRequest viewRequest = new ViewVoterRequest();
       viewRequest.setId(200L);

       voterService.viewVoter(viewRequest);
       
       assertNotNull(voterService.viewVoter(viewRequest));
       assertTrue(voterService.viewVoter(viewRequest).getFirstName().contains("newfirstname"));
       assertTrue(voterService.viewVoter(viewRequest).getLastName().contains("newvoter@gmail.com"));

    }


}
