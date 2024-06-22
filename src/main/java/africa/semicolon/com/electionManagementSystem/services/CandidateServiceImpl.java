package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RemoveCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateCandidateRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.UpdateCandidateResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.CandidateAlreadyExistsException;
import africa.semicolon.com.electionManagementSystem.exceptions.CandidateNotFoundException;
import africa.semicolon.com.electionManagementSystem.models.Candidate;
import africa.semicolon.com.electionManagementSystem.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CandidateServiceImpl implements CandidateService{

    private final ModelMapper modelMapper;
    private final CandidateRepository candidateRepository;
    private final EmailService emailService;

    @Override
    public RegisterCandidateResponse registerCandidate(RegisterCandidateRequest candidateRequest) {
        Candidate candidate = modelMapper.map(candidateRequest, Candidate.class);
        verifyCandidate(candidateRequest.getEmail());
        checkCandidateAge(candidate.getDateOfBirth());
        candidateRepository.save(candidate);
        RegisterCandidateResponse response = modelMapper.map(candidate, RegisterCandidateResponse.class);
        response.setMessage("candidate registration successful");
        emailService.sendEmail(candidate.getEmail(), "Election Service Team\n",
                "Hello " + candidate.getFirstName() + " " + candidate.getLastName() +
                "\nYou registration was successful.\n" +
                        "You are now one of the aspired "
                        + candidate.getPositionContested() + " candidate");
        return response;
    }

    public void checkCandidateAge(LocalDate dateOfBirth){
        LocalDate currentDate = LocalDate.now();
        if(currentDate.getYear() - dateOfBirth.getYear() < 35){
            throw new RuntimeException("candidate must be at least 35 years old");
        }
    }

    public void verifyCandidate(String email){
        Candidate candidate = candidateRepository.findByEmail(email);
        if (candidate != null){
            throw new CandidateAlreadyExistsException("candidate with same email already exists");
        }
    }


    @Override
    public Candidate findCandidateById(long id) {
        return candidateRepository.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException("candidate not found"));
    }

    @Override
    public List<Candidate> findAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public RemoveCandidateResponse removeCandidate(RemoveCandidateRequest removeCandidateRequest) {
        Candidate candidate = findCandidateById(removeCandidateRequest.getId());
        candidateRepository.delete(candidate);
        RemoveCandidateResponse response = modelMapper.map(candidate, RemoveCandidateResponse.class);
        response.setMessage("candidate removed successfully");
        return response;
    }

    @Override
    public UpdateCandidateResponse updateCandidate(UpdateCandidateRequest updateCandidateRequest) {
        Candidate candidate = findCandidateById(updateCandidateRequest.getCandidateId());
        candidate.setParty(updateCandidateRequest.getParty());
        candidate.setPositionContested(updateCandidateRequest.getPositionContested());
        candidateRepository.save(candidate);
        UpdateCandidateResponse response = modelMapper.map(candidate, UpdateCandidateResponse.class);
        response.setMessage("candidate updated successfully");
        return response;
    }

    @Override
    public long getNoOfCandidates() {
        return candidateRepository.count();
    }

}
