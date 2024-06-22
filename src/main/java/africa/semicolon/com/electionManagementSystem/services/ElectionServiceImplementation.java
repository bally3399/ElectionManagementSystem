package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddCandidateToElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CancelElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.RemoveCandidateFromElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddCandidateToElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.RemoveCandidateFromElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.AdminNotInvolvedInElectionException;
import africa.semicolon.com.electionManagementSystem.exceptions.ElectionNotFoundException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionDateException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionTimeException;
import africa.semicolon.com.electionManagementSystem.models.Admin;
import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.repository.ElectionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ElectionServiceImplementation implements ElectionService {

    private final ElectionRepository electionRepository;
    private final ModelMapper modelMapper;
    private AdminService adminService;

    @Lazy
    @Autowired
    public void setAdminService(AdminService adminService){
        this.adminService = adminService;
    }

    @Override
    public ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest) {
        Admin admin = adminService.findAdminById(scheduleElectionRequest.getAdminId());
        Election election = modelMapper.map(scheduleElectionRequest, Election.class);
        validateElectionDates(scheduleElectionRequest, election);
        validateElectionTimes(scheduleElectionRequest, election);
        election.setAdmin(admin);
        electionRepository.save(election);
        return modelMapper.map(election, ScheduleElectionResponse.class);
    }

    @Override
    public CancelElectionResponse cancelElection(CancelElectionRequest cancelElectionRequest) {
        Admin admin = adminService.findAdminById(cancelElectionRequest.getAdminId());
        Election election = getElectionById(cancelElectionRequest.getElectionId());
        if(!election.getAdmin().getId().equals(admin.getId())) throw new AdminNotInvolvedInElectionException("Admin is not involved in this election.");
        CancelElectionResponse cancelElectionResponse = modelMapper.map(election, CancelElectionResponse.class);
        electionRepository.delete(election);
        return cancelElectionResponse;
    }

    @Override
    public Election getElectionById(Long electionId) {
        return electionRepository.findById(electionId).
                orElseThrow(()-> new ElectionNotFoundException("Election not does not exist."));
    }

    @Override
    public AddCandidateToElectionResponse addCandidateToElection(AddCandidateToElectionRequest addCandidateToElectionRequest) {
        Election election = getElectionById(addCandidateToElectionRequest.getElectionId());
        Admin admin = adminService.findAdminById(addCandidateToElectionRequest.getAdminId());
        if(!election.getAdmin().getId().equals(admin.getId())) throw new AdminNotInvolvedInElectionException("Admin is not involved in this election.");
        // validate if candidate is in two elections
        //make sure you find and add the candidate;
        return null;
    }

    @Override
    public RemoveCandidateFromElectionResponse removeCandidateFromElection(RemoveCandidateFromElectionRequest removeCandidateFromElectionRequest) {
        Election election = getElectionById(removeCandidateFromElectionRequest.getElectionId());
        Admin admin = adminService.findAdminById(removeCandidateFromElectionRequest.getAdminId());
        if(!election.getAdmin().getId().equals(admin.getId())) throw new AdminNotInvolvedInElectionException("Admin is not involved in this election.");
        //make sure you find and add the candidate;
        return null;
    }

    private void validateElectionTimes(ScheduleElectionRequest scheduleElectionRequest, Election election) {
        election.setStartTime(parseStringToLocalTime(scheduleElectionRequest.getStartTime()));
        election.setEndTime(parseStringToLocalTime(scheduleElectionRequest.getEndTime()));
    }

    private void validateElectionDates(ScheduleElectionRequest scheduleElectionRequest, Election election) {
        election.setStartDate(parseStringToLocalDate(scheduleElectionRequest.getStartDate()));
        election.setEndDate(parseStringToLocalDate(scheduleElectionRequest.getEndDate()));
    }

    private LocalDate parseStringToLocalDate(String electionDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
        try{
            return LocalDate.parse(electionDate, format);
        }
        catch (Exception error) {
            throw new InvalidElectionDateException("Please enter a valid date with format - d/m/yyyy");
        }
    }

    private LocalTime parseStringToLocalTime(String electionTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm");
        try {
            return LocalTime.parse(electionTime, format);
        } catch (Exception error) {
            throw new InvalidElectionTimeException("Please enter a valid 24-hour time format - 12:00");
        }
    }


}
