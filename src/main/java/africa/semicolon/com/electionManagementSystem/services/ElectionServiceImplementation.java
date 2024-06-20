package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests.CancelElectionRequest;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.responses.CancelElectionResponse;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionDateException;
import africa.semicolon.com.electionManagementSystem.exceptions.InvalidElectionTimeException;
import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.repository.ElectionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class ElectionServiceImplementation implements ElectionService {

    private final ElectionRepository electionRepository;
    private final ModelMapper modelMapper;

    @Override
    public ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest) {
        Election election = modelMapper.map(scheduleElectionRequest, Election.class);
        validateElectionDates(scheduleElectionRequest, election);
        validateElectionTimes(scheduleElectionRequest, election);
        electionRepository.save(election);
        System.out.println(election);
        return modelMapper.map(election, ScheduleElectionResponse.class);
    }

    @Override
    public CancelElectionResponse cancelElection(CancelElectionRequest cancelElectionRequest) {
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
