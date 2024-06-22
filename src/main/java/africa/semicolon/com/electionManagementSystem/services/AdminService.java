package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.*;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
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

    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest);

    CancelElectionResponse cancelElection(CancelElectionRequest cancelElectionRequest);

    RegisterCandidateResponse registerCandidate(RegisterCandidateRequest candidateRequest);
   }


