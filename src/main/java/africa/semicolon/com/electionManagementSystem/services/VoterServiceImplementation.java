package africa.semicolon.com.electionManagementSystem.services;
import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.exceptions.UnderAgeVoterException;
import africa.semicolon.com.electionManagementSystem.exceptions.VoterAlreadyExistException;
import africa.semicolon.com.electionManagementSystem.models.Voter;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class VoterServiceImplementation implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;

    @Override
    public RegisterVoterResponse register(RegisterVoterRequest registerVoterRequest) {
        verifyVoterEligibility(registerVoterRequest);
        Voter voter = modelMapper.map(registerVoterRequest, Voter.class);
            voter = voterRepository.save(voter);
            RegisterVoterResponse response = modelMapper.map(voter, RegisterVoterResponse.class);
            response.setMessage("Voter Registered Successfully");
            return response;
        }

    private void verifyVoterEligibility(RegisterVoterRequest registerVoterRequest) {
        String email = registerVoterRequest.getEmail();
        String phoneNumber = registerVoterRequest.getPhoneNumber();
        verifyVoterBy(email, phoneNumber);
        if (registerVoterRequest.getDateOfBirth().plusYears(18).isAfter(LocalDate.now())) {
        throw new UnderAgeVoterException("Under age voter not eligible for registration");
        }
    }

    private void verifyVoterBy(String email, String phone) {
        Voter voter = voterRepository.findByEmailAndPhoneNumber(email, phone);
        if(voter != null) {
            throw new VoterAlreadyExistException("Voter already exist");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();


        return null;
    }

}

