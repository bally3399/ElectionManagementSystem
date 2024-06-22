package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.*;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
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
