package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.*;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
import africa.semicolon.com.electionManagementSystem.dtos.requests.AddAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.DeleteAdminRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.DeleteAdminResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.AdminNotFoundException;
import africa.semicolon.com.electionManagementSystem.exceptions.UserAlreadyExistException;
import africa.semicolon.com.electionManagementSystem.models.Admin;
import africa.semicolon.com.electionManagementSystem.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final ModelMapper modelMapper;
    private final AdminRepository adminRepository;
    private final ElectionService electionService;
    private final CandidateService candidateService;



    @Override
    public AddAdminResponse addAdmin(AddAdminRequest addAdminRequest) {
        String email = addAdminRequest.getEmail();
        verifyAdmin(email);
        Admin admin = modelMapper.map(addAdminRequest, Admin.class);
        adminRepository.save(admin);
        AddAdminResponse addAdminResponse = modelMapper.map(admin, AddAdminResponse.class);
        addAdminResponse.setMessage("Successfully added admin");
        return addAdminResponse;
    }

    @Override
    public Admin findByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email.toLowerCase());
        if(admin == null) {
            throw new AdminNotFoundException("Admin not found");
        }
        return admin;
    }

    @Override
    public DeleteAdminResponse deleteAdmin(DeleteAdminRequest deleteAdminRequest) {
        Admin admin = findByEmail(deleteAdminRequest.getEmail().toLowerCase());
        adminRepository.delete(admin);
        DeleteAdminResponse response = modelMapper.map(admin, DeleteAdminResponse.class);
        response.setMessage("Admin deleted successfully");
        return response;
    }

    @Override
    public Admin findAdminById(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(()-> new AdminNotFoundException("Admin does not exist."));
    }

    @Override
    public ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest) {
        return electionService.scheduleElection(scheduleElectionRequest);
    }

    @Override
    public CancelElectionResponse cancelElection(CancelElectionRequest cancelElectionRequest) {
        findAdminById(cancelElectionRequest.getAdminId());
        return electionService.cancelElection(cancelElectionRequest);

    }

    @Override
    public RegisterCandidateResponse registerCandidate(RegisterCandidateRequest candidateRequest) {
        findAdminById(candidateRequest.getAdminId());
        return candidateService.registerCandidate(candidateRequest);
    }
    public void verifyAdmin(String email) {
            Admin admin = adminRepository.findByEmail(email);
            if(admin != null) {
                throw new UserAlreadyExistException("Admin with same email already exist");
            }
    }


}
