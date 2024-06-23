package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.*;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
import africa.semicolon.com.electionManagementSystem.exceptions.AdminNotFoundException;
import africa.semicolon.com.electionManagementSystem.exceptions.UserAlreadyExistException;
import africa.semicolon.com.electionManagementSystem.models.Admin;
import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.models.ElectionStatus;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static africa.semicolon.com.electionManagementSystem.models.Category.NATIONAL;
import static africa.semicolon.com.electionManagementSystem.models.Party.APC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ElectionService electionService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private VoterService voterService;
    @Autowired
    private VoterRepository voterRepository;

    @Test
    public void addAdminTest(){
        AddAdminRequest addAdminRequest = new AddAdminRequest();
        addAdminRequest.setPassword("1234");
        addAdminRequest.setFirstName("Lawal");
        addAdminRequest.setLastName("Toheeb");
        addAdminRequest.setEmail("Lawaltoheeb@email.com");
        AddAdminResponse response = adminService.addAdmin(addAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Successfully added admin");
    }


    @Test
    public void addAdminWithSameUsername_throwsUserAlreadyExistsException(){
        try {
            AddAdminRequest addAdminRequest = new AddAdminRequest();
            addAdminRequest.setPassword("1234");
            addAdminRequest.setFirstName("Sulaiman");
            addAdminRequest.setLastName("Bally");
            addAdminRequest.setEmail("ballyOne@email.com");
            AddAdminResponse response = adminService.addAdmin(addAdminRequest);
            assertNotNull(response);
            assertThat(response.getMessage()).isEqualTo("Successfully added admin");
        }catch (UserAlreadyExistException e){
            assertThat(e.getMessage()).isEqualTo("Admin with same email already exist");
        }

    }


    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void findAdminById(){
    Admin admin = adminService.findAdminById(100L);
    assertNotNull(admin);
}

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void deleteAdminTest(){
        Admin admin = adminService.findByEmail("admin2@email.com");
        assertNotNull(admin);
        DeleteAdminRequest deleteAdminRequest = new DeleteAdminRequest();
        deleteAdminRequest.setEmail("admin2@email.com");
        DeleteAdminResponse response = adminService.deleteAdmin(deleteAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin deleted successfully");
        try{
            admin = adminService.findByEmail("admin2@email.com");
            assertNull(admin);
        } catch (AdminNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("Admin not found");
        }

    }

@Test
@Sql(scripts = {"/db/data.sql"})
public void adminCanScheduleElectionTest() {
    ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
    scheduleElectionRequest.setAdminId(100L);
    scheduleElectionRequest.setCategory(NATIONAL);
    scheduleElectionRequest.setTitle("Lagos State Governorship Election");
    scheduleElectionRequest.setLocation("Lagos");
    scheduleElectionRequest.setStartDate("3/9/2024");
    scheduleElectionRequest.setStartTime("7:00");
    scheduleElectionRequest.setEndDate("15/9/2024");
    scheduleElectionRequest.setEndTime("23:00");

    ScheduleElectionResponse scheduleElectionResponse = adminService.scheduleElection(scheduleElectionRequest);

    assertThat(scheduleElectionResponse).isNotNull();
    assertEquals("Lagos State Governorship Election", scheduleElectionResponse.getTitle());
}

@Test
@Sql(scripts = {"/db/data.sql"})
public void updateElectionStatusTest(){
    UpdateElectionStatusRequest updateElectionStatusRequest = new UpdateElectionStatusRequest();
    updateElectionStatusRequest.setElectionId(303L);
    updateElectionStatusRequest.setAdminId(100L);
    updateElectionStatusRequest.setElectionStatus(ElectionStatus.ONGOING);
    UpdateElectionStatusResponse updateElectionStatusResponse = adminService.updateElectionStatus(updateElectionStatusRequest);
    Election election = electionService.getElectionById(303L);

    assertThat(updateElectionStatusResponse).isNotNull();
    assertEquals(303L, updateElectionStatusResponse.getElectionId());
    assertEquals(ElectionStatus.ONGOING, election.getElectionStatus());
}

@Test
@Sql(scripts = {"/db/data.sql"})
public void registerCandidate(){
    Admin admin = adminService.findAdminById(100L);
    assertNotNull(admin);
    RegisterCandidateRequest candidateRequest = new RegisterCandidateRequest();
    candidateRequest.setFirstName("King");
    candidateRequest.setLastName("Jumong");
    candidateRequest.setEmail("jumong@gmail.com");
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

//    @Test
//    @Sql(scripts = {"/db/data.sql"})
//    public void registerVoter(){
//        Admin admin = adminService.findAdminById(100L);
//        assertThat(admin).isNotNull();
//        RegisterVoterRequest registerVoterRequest = new RegisterVoterRequest();
//        registerVoterRequest.setFirstName("newfirstname");
//        registerVoterRequest.setLastName("newlastname");
//        registerVoterRequest.setAdminId(100L);
//        Address address = new Address();
//        address.setBuildingNumber("00123");
//        address.setWard("newWardName");
//        address.setLocalGovernmentArea("newLocalGovtArea");
//        address.setCity("newCityName");
//        address.setState("newStateName");
//        registerVoterRequest.setAddress(address);
//        registerVoterRequest.setPhoneNumber("001234567");
//        registerVoterRequest.setEmail("newvoter@gmail.com");
//        registerVoterRequest.setDateOfBirth("21/10/1990");
//        RegisterVoterResponse registerVoterResponse = voterService.register(registerVoterRequest);
//        assertNotNull(registerVoterResponse);
//        assertTrue(registerVoterResponse.getMessage().contains("Voter Registered Successfully"));
//        assertEquals(6,voterRepository.findAll().size());
//    }


}
