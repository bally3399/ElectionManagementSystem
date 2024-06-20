package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests.CancelElectionRequest;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.responses.CancelElectionResponse;
import africa.semicolon.com.electionManagementSystem.dataTransferObjects.responses.ScheduleElectionResponse;

public interface ElectionService {

    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest);

    CancelElectionResponse cancelElection(CancelElectionRequest cancelElectionRequest);
}
