package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.DeleteAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.DeleteAdminResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.AdminNotFoundException;
import africa.semicolon.com.electionManagementSystem.exceptions.UserAlreadyExistException;
import africa.semicolon.com.electionManagementSystem.models.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void addAdminTest(){
        AddAdminRequest addAdminRequest = new AddAdminRequest();
        addAdminRequest.setUsername("Olayinka1");
        addAdminRequest.setPassword("1234");
        addAdminRequest.setFirstName("Sulaiman");
        addAdminRequest.setLastName("Bally");
        addAdminRequest.setEmail("ballyOne@email.com");
        AddAdminResponse response = adminService.addAdmin(addAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Successfully added admin");
    }
    @Test
    public void addAdminWithSameUsername_throwsUserAlreadyExistsException(){
        try {
            AddAdminRequest addAdminRequest = new AddAdminRequest();
            addAdminRequest.setUsername("Olayinka1");
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
    public void findAdminTest(){
        Admin admin = adminService.findByEmail("ballyOne@email.com");
        assertNotNull(admin);
    }

    @Test
    public void deleteAdminTest(){
        Admin admin = adminService.findByEmail("ballyOne@email.com");
        assertNotNull(admin);
        DeleteAdminRequest deleteAdminRequest = new DeleteAdminRequest();
        deleteAdminRequest.setEmail("ballyOne@email.com");
        DeleteAdminResponse response = adminService.deleteAdmin(deleteAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin deleted successfully");
        try{
            admin = adminService.findByEmail("ballyOne@email.com");
            assertNull(admin);
        } catch (AdminNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("Admin not found");
        }

    }

}