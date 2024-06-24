package africa.semicolon.com.electionManagementSystem.controller;


import africa.semicolon.com.electionManagementSystem.dtos.requests.AddAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.DeleteAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.DeleteAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts ={"/db/data.sql"})
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Test
    public void testAddAdminUserAlreadyExist() {
        AddAdminRequest addAdminRequest = new AddAdminRequest();
        addAdminRequest.setFirstName("BimBim");
        addAdminRequest.setLastName("addicted");
        addAdminRequest.setEmail("admin1@email.com");
        addAdminRequest.setPassword("password");

        ResponseEntity<AddAdminResponse> response = adminController.addAdmin(addAdminRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }


    @Test
    public void testAddAdminSuccess() {
        AddAdminRequest addAdminRequest = new AddAdminRequest();
        addAdminRequest.setFirstName("BimBim");
        addAdminRequest.setLastName("addicted");
        addAdminRequest.setEmail("newadmin@example.com");
        addAdminRequest.setPassword("password");

        ResponseEntity<AddAdminResponse> responseEntity = adminController.addAdmin(addAdminRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    public void testDeleteAdminSuccess() {
        DeleteAdminRequest deleteAdminRequest = new DeleteAdminRequest();
        deleteAdminRequest.setEmail("admin1@email.com");

        ResponseEntity<DeleteAdminResponse> response = adminController.deleteAdmin(deleteAdminRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteAdminNotFound() {
        DeleteAdminRequest deleteAdminRequest = new DeleteAdminRequest();
        deleteAdminRequest.setEmail("PenIsUp@email.com");

        ResponseEntity<DeleteAdminResponse> response = adminController.deleteAdmin(deleteAdminRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testScheduleElectionSuccess() {
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(100L);
        scheduleElectionRequest.setLocation("Semi_Colon");
        scheduleElectionRequest.setStartDate("1/10/2024");
        scheduleElectionRequest.setStartTime("09:00");
        scheduleElectionRequest.setEndTime("17:00");
        scheduleElectionRequest.setEndDate("6/10/2024");
        scheduleElectionRequest.setTitle("Presidential Election");
        scheduleElectionRequest.setCategory(Category.NATIONAL);


        ResponseEntity<ScheduleElectionResponse> response = adminController.scheduleElection(scheduleElectionRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testScheduleElectionAdminNotFound() {
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(102L);
        scheduleElectionRequest.setLocation("Semi_Colon");
        scheduleElectionRequest.setStartDate("6/10/2024");
        scheduleElectionRequest.setStartTime("09:00");
        scheduleElectionRequest.setEndTime("17:00");
        scheduleElectionRequest.setEndDate("10/10/2024");
        scheduleElectionRequest.setTitle("Presidential Election");
        scheduleElectionRequest.setCategory(Category.NATIONAL);


        ResponseEntity<ScheduleElectionResponse> response = adminController.scheduleElection(scheduleElectionRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testScheduleElectionGeneralError() {
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(100L);
        scheduleElectionRequest.setLocation("Semi_Colon");
        scheduleElectionRequest.setStartDate("2024-12-31");
        scheduleElectionRequest.setStartTime("09:00");
        scheduleElectionRequest.setEndTime("17:00");
        scheduleElectionRequest.setEndDate("2024-12-31");
        scheduleElectionRequest.setTitle("Presidential Election");
        scheduleElectionRequest.setCategory(Category.NATIONAL);


        ResponseEntity<ScheduleElectionResponse> response = adminController.scheduleElection(scheduleElectionRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

