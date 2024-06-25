package africa.semicolon.com.electionManagementSystem.controller;

import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.models.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts ={"/db/data.sql"})
public class VoterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testVoterController() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
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

        mockMvc.perform(post("/api/voters/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(registerVoterRequest))
        ).andExpect(status().isCreated()).andDo(print());
    }

    @Test
    public void testViewVoterInformation() throws Exception {

    }

}
