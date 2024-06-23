package africa.semicolon.com.electionManagementSystem.services;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CastBallotRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.LoginRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ViewVoterRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CastBallotResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.LoginResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RegisterVoterResponse;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RegisterVoterRequest;
import africa.semicolon.com.electionManagementSystem.exceptions.*;
import africa.semicolon.com.electionManagementSystem.models.Candidate;
import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.models.Vote;
import africa.semicolon.com.electionManagementSystem.models.Voter;
import africa.semicolon.com.electionManagementSystem.repository.VoteRepository;
import africa.semicolon.com.electionManagementSystem.repository.VoterRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class VoterServiceImplementation implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;
    private final VoteRepository voteRepository;
    private CandidateService candidateService;
    private ElectionService electionService;

    @Lazy
    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Lazy
    @Autowired
    public void setElectionService(ElectionService electionService) {
        this.electionService = electionService;
    }



    @Override
    public RegisterVoterResponse register(RegisterVoterRequest registerVoterRequest) {
        LocalDate dateOfBirth = verifyVoterEligibility(registerVoterRequest);
        Voter voter = modelMapper.map(registerVoterRequest, Voter.class);
        voter.setDateOfBirth(dateOfBirth);
        voter.setVoterNumber(generateVoterNumber());
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
        verifyVoterBy(registerVoterRequest.getNationalIdentificationNumber());
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

    private void verifyVoterBy(String nationalIdentificationNumber) {
        Voter voter = voterRepository.findByNationalIdentificationNumber(nationalIdentificationNumber);
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

    @Override
    public Voter findVoterById(Long id) {
        return voterRepository.findById(id)
                .orElseThrow(()-> new InValidVoterException("Voter does not exist"));
    }

    @Override
    public CastBallotResponse castBallot(CastBallotRequest castBallotRequest) {
        Voter voter = findVoterById(castBallotRequest.getVoterId());
        Candidate candidate = candidateService.findCandidateById(castBallotRequest.getCandidateId());
        Election election = candidate.getElection();
        // you can validate the election exist in the election model
        Vote vote = modelMapper.map(castBallotRequest ,Vote.class);
        vote.setElection(election);
        voteRepository.save(vote);
        election.getVotes().add(vote);
        voter.getVoteHistory().add(vote);
        electionService.saveElection(election);
        voterRepository.save(voter);
        return modelMapper.map(vote, CastBallotResponse.class);
    }

    private Voter getVoter(ViewVoterRequest viewRequest) {
        return voterRepository.findById(viewRequest.getId())
                .orElseThrow(()-> new InValidVoterException("Voter does not exist"));
    }

    private String generateVoterNumber() {
        SecureRandom secureRandom = new SecureRandom();
        long newVoterNumber = secureRandom.nextLong(1_000_000_000L,100_000_000_000L);
        while (voterRepository.findByVoterNumber((newVoterNumber +"")) != null) {
            newVoterNumber = secureRandom.nextLong(1_000_000_000L,100_000_000_000L);
        }
        return "" + newVoterNumber;
    }



}

