package africa.semicolon.com.electionManagementSystem.services;
import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.exceptions.*;
import africa.semicolon.com.electionManagementSystem.models.Voter;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class VoterServiceImplementation implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;
    @Override
    public RegisterVoterResponse register(RegisterVoterRequest registerVoterRequest) {
        LocalDate dateOfBirth = verifyVoterEligibility(registerVoterRequest);
        Voter voter = modelMapper.map(registerVoterRequest, Voter.class);
        voter.setDateOfBirth(dateOfBirth);
        System.out.println(voter);
            voter = voterRepository.save(voter);
            RegisterVoterResponse response = modelMapper.map(voter, RegisterVoterResponse.class);
            response.setMessage("Voter Registered Successfully");
            return response;
        }

    private LocalDate verifyVoterEligibility(RegisterVoterRequest registerVoterRequest) {
        String email = registerVoterRequest.getEmail();
        String phoneNumber = registerVoterRequest.getPhoneNumber();
        verifyVoterBy(email, phoneNumber);
        LocalDate dateOfBirth = parseStringToLocalDate(registerVoterRequest.getDateOfBirth());

        if (dateOfBirth.plusYears(18).isAfter(LocalDate.now())) {
        throw new UnderAgeVoterException("Under age voter not eligible for registration");
        }
        return dateOfBirth;
    }

    private void verifyVoterBy(String email, String phone) {
        Voter voter = voterRepository.findByEmailAndPhoneNumber(email, phone);
        if(voter != null) {
            throw new VoterAlreadyExistException("Voter already exist");
        }
    }


    private LocalDate parseStringToLocalDate(String dateOfBirth) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
        try{
            return LocalDate.parse(dateOfBirth, format);
        }
        catch (Exception error) {
            throw new InvalidDateOfBirthException("Please enter a valid date of birth with format - d/m/yyyy");
        }
    }



    @Override
    public Voter viewVoter(ViewVoterRequest viewRequest) {
        return getVoter(viewRequest);


    }

    private Voter getVoter(ViewVoterRequest viewRequest) {
        return voterRepository.findById(viewRequest.getId())
                .orElseThrow(()-> new InValidVoterException("Voter does not exist"));
    }


}

