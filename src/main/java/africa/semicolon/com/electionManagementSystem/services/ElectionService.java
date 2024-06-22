package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.AddCandidateToElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.CancelElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.AddCandidateToElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.CancelElectionResponse;
import africa.semicolon.com.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import africa.semicolon.com.electionManagementSystem.models.Election;

public interface ElectionService {

    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest);

    CancelElectionResponse cancelElection(CancelElectionRequest cancelElectionRequest);

    Election getElectionById(Long electionId);

    AddCandidateToElectionResponse addCandidateToElection(AddCandidateToElectionRequest addCandidateToElectionRequest);


}
