package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.DeleteAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.DeleteAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.models.Admin;

public interface AdminService {
    AddAdminResponse addAdmin(AddAdminRequest addAdminRequest);

    Admin findByEmail(String email);

    DeleteAdminResponse deleteAdmin(DeleteAdminRequest deleteAdminRequest);

    Admin findAdminById(Long adminId);
<<<<<<< HEAD
=======

    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest);
>>>>>>> dfe2a5ed89c019bbfa5c3f676cd7660322f2d5a6

    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest);
}
