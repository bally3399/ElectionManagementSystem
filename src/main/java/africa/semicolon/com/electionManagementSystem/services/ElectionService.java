package africa.semicolon.com.electionManagementSystem.services;

import africa.semicolon.com.electionManagementSystem.dtos.requests.UpdateElectionStatusRequest;
import africa.semicolon.com.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import africa.semicolon.com.electionManagementSystem.dtos.responses.*;
import africa.semicolon.com.electionManagementSystem.models.Election;

public interface ElectionService {

    ScheduleElectionResponse scheduleElection(ScheduleElectionRequest scheduleElectionRequest);

    UpdateElectionStatusResponse updateElectionStatus(UpdateElectionStatusRequest updateElectionStatusRequest);

    Election getElectionById(Long electionId);

    Election saveElection(Election election);

//    AddCandidateToElectionResponse addCandidateToElection(AddCandidateToElectionRequest addCandidateToElectionRequest);
//
//
//    RemoveCandidateFromElectionResponse removeCandidateFromElection(RemoveCandidateFromElectionRequest removeCandidateFromElectionRequest);
}
