package africa.semicolon.com.electionManagementSystem.controller;//package africa.semicolon.com.electionManagementSystem.controller;
//
//import africa.semicolon.com.electionManagementSystem.dtos.requests.AddAdminRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.CancelElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.DeleteAdminRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.AddAdminResponse;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelElectionResponse;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.DeleteAdminResponse;
//import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
//import africa.semicolon.com.electionManagementSystem.exceptions.AdminNotFoundException;
//import africa.semicolon.com.electionManagementSystem.exceptions.ElectionNotFoundException;
//import africa.semicolon.com.electionManagementSystem.exceptions.UserAlreadyExistException;
//import africa.semicolon.com.electionManagementSystem.services.AdminServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AdminControllerTest {
//
//    @Mock
//    private AdminServiceImpl adminService;
//
//    @InjectMocks
//    private AdminController adminController;
//
////    @Test
////    public void testAddAdmin_Succeeds() throws UserAlreadyExistException {
////        AddAdminRequest request = new AddAdminRequest("email", "password");
//////        AddAdminResponse response = new AddAdminResponse("Admin added successfully");
////        when(adminService.addAdmin(request)).thenReturn(response);
////
////        ResponseEntity<AddAdminResponse> result = adminController.addAdmin(request);
////
////        assertEquals(HttpStatus.CREATED, result.getStatusCode());
////        assertEquals(response, result.getBody());
////    }
//
//    @Test
//    public void testAddAdmin_Fails_DuplicateEmail() throws UserAlreadyExistException {
//        AddAdminRequest request = new AddAdminRequest("email", "password");
//        when(adminService.addAdmin(request)).thenThrow(new UserAlreadyExistException("Admin with same email already exists"));
//
//        ResponseEntity<AddAdminResponse> result = adminController.addAdmin(request);
//
//        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
//    }
//
//    @Test
//    public void testDeleteAdmin_Succeeds() throws AdminNotFoundException {
//        DeleteAdminRequest request = new DeleteAdminRequest(1L);
//        DeleteAdminResponse response = new DeleteAdminResponse("Admin deleted successfully");
//        when(adminService.deleteAdmin(request)).thenReturn(response);
//
//        ResponseEntity<DeleteAdminResponse> result = adminController.deleteAdmin(request);
//
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    public void testDeleteAdmin_Fails_AdminNotFound() throws AdminNotFoundException {
//        DeleteAdminRequest request = new DeleteAdminRequest(1L);
//        when(adminService.deleteAdmin(request)).thenThrow(new AdminNotFoundException("Admin not found"));
//
//        ResponseEntity<DeleteAdminResponse> result = adminController.deleteAdmin(request);
//
//        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//    }
//
//    @Test
//    public void testScheduleElection_Succeeds() throws AdminNotFoundException {
//        ScheduleElectionRequest request = new ScheduleElectionRequest(1L, "title", "description");
//        ScheduleElectionResponse response = new ScheduleElectionResponse();
//        when(adminService.scheduleElection(request)).thenReturn(response);
//
//        ResponseEntity<ScheduleElectionResponse> result = adminController.scheduleElection(request);
//
//        assertEquals(HttpStatus.CREATED, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    public void testScheduleElection_Fails_AdminNotFound() throws AdminNotFoundException {
//        ScheduleElectionRequest request = new ScheduleElectionRequest(1L, "title", "description");
//        when(adminService.scheduleElection(request)).thenThrow(new AdminNotFoundException("Admin not found"));
//
//        ResponseEntity<ScheduleElectionResponse> result = adminController.scheduleElection(request);
//
//        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
//    }
//
//    @Test
//    public void testCancelElection_Succeeds() throws ElectionNotFoundException {
//        CancelElectionRequest request = new CancelElectionRequest(1L);
//        CancelElectionResponse response = new CancelElectionResponse();
//        when(adminService.cancelElection(request)).thenReturn(response);
//
//        ResponseEntity<CancelElectionResponse> result = adminController.cancelElection(request);
//
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(response, result.getBody());
//    }
//
//    @Test
//    public void testCancelElection_Fails_ElectionNotFound() throws ElectionNotFoundException {
//        CancelElectionRequest request = new CancelElectionRequest(1L);
//        try {
//            when(adminService.cancelElection(request)).thenThrow(new ElectionNotFoundException("Election not found"));
//            ResponseEntity<CancelElectionResponse> result = adminController.cancelElection(request);
//            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
//        } catch (ElectionNotFoundException e) {
//            throw e;
//        }
//    }
//
//}

import africa.semicolon.com.electionManagementSystem.controller.AdminController;
import africa.semicolon.com.electionManagementSystem.dtos.requests.AddAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.DeleteAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.DeleteAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.is;



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
    public void testAddAdminGeneralError() {
        AddAdminRequest addAdminRequest = new AddAdminRequest();
        addAdminRequest.setFirstName("BimBim");
        addAdminRequest.setLastName("addicted");
        addAdminRequest.setEmail("newadmin@example.com");
        addAdminRequest.setPassword("password");

        ResponseEntity<AddAdminResponse> response = adminController.addAdmin(addAdminRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
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
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
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
    public void testDeleteAdminGeneralError() {
        DeleteAdminRequest deleteAdminRequest = new DeleteAdminRequest();
        deleteAdminRequest.setEmail("penIsUp@email.com");

        ResponseEntity<DeleteAdminResponse> response = adminController.deleteAdmin(deleteAdminRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testScheduleElectionSuccess() {
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

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testScheduleElectionAdminNotFound() {
        ScheduleElectionRequest scheduleElectionRequest = new ScheduleElectionRequest();
        scheduleElectionRequest.setAdminId(102L);
        scheduleElectionRequest.setLocation("Semi_Colon");
        scheduleElectionRequest.setStartDate("2024-12-31");
        scheduleElectionRequest.setStartTime("09:00");
        scheduleElectionRequest.setEndTime("17:00");
        scheduleElectionRequest.setEndDate("2024-12-31");
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




