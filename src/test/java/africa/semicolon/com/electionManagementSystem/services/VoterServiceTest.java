package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class VoterServiceTest {

    VoterService voterService;


    @Test
    public void testToLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("1245");
        loginRequest.setUsername("chichi");
        LoginResponse response = voterService.login(loginRequest);



        assertNotNull(response);
        assertTrue(response.getMessage().contains("success"));

    }

}
