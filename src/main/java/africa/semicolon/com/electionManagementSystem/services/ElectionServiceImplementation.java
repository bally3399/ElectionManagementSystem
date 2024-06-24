package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.FindElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateElectionStatusRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
import africa.semicolon.com.electionManagementSystem.exceptions.ElectionNotFoundException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionDateException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionStatusException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionTimeException;
import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.models.ElectionStatus;
import africa.semicolon.com.electionManagementSystem.repository.ElectionRepository;

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

    private CandidateService candidateService;

    @Lazy
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Lazy
    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest) {
        Election election = modelMapper.map(scheduleElectionRequest, Election.class);
        validateElectionDates(scheduleElectionRequest, election);
        validateElectionTimes(scheduleElectionRequest, election);
        electionRepository.save(election);
        return modelMapper.map(election, ScheduleElectionResponse.class);
    }

    @Override
    public UpdateElectionStatusResponse updateElectionStatus(UpdateElectionStatusRequest updateElectionStatusRequest) {
        Election election = getElectionById(updateElectionStatusRequest.getElectionId());
        if (updateElectionStatusRequest.getElectionStatus() == null) throw new InvalidElectionStatusException("Please enter a valid status for the election.");
        election.setElectionStatus(updateElectionStatusRequest.getElectionStatus());
        electionRepository.save(election);
        return modelMapper.map(election, UpdateElectionStatusResponse.class);
    }

    @Override
    public Election getElectionById(Long electionId) {
        return electionRepository.findById(electionId).
                orElseThrow(()-> new ElectionNotFoundException("Election not does not exist."));
    }

    @Override
    public Election saveElection(Election election) {
        return electionRepository.save(election);
    }

    @Override
    public UpdateElectionResponse updateElection(UpdateElectionRequest updateElectionRequest) {
        Election election = getElectionById(updateElectionRequest.getElectionId());
        if (updateElectionRequest.getTitle() != null) election.setTitle(updateElectionRequest.getTitle());
        if (updateElectionRequest.getElectionStatus() != null) election.setElectionStatus(updateElectionRequest.getElectionStatus());
        if (updateElectionRequest.getLocation() != null) election.setLocation(updateElectionRequest.getLocation());
        if (updateElectionRequest.getCategory() != null) election.setElectionStatus(updateElectionRequest.getElectionStatus());
        if (updateElectionRequest.getStartDate() != null) election.setStartDate(parseStringToLocalDate(updateElectionRequest.getStartDate()));
        if (updateElectionRequest.getEndDate() != null) election.setStartDate(parseStringToLocalDate(updateElectionRequest.getEndDate()));
        if (updateElectionRequest.getStartTime() != null) election.setStartTime(parseStringToLocalTime(updateElectionRequest.getStartTime()));
        if (updateElectionRequest.getEndTime() != null) election.setEndTime(parseStringToLocalTime(updateElectionRequest.getEndTime()));
        electionRepository.save(election);
        return modelMapper.map(election, UpdateElectionResponse.class);
    }

    @Override
    public FindElectionResponse findElection(FindElectionRequest findElectionRequest) {
        Election election = getElectionById(findElectionRequest.getElectionId());
        return modelMapper.map(election, FindElectionResponse.class);
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
