package africa.semicolon.com.electionManagementSystem.repository;

import africa.semicolon.com.electionManagementSystem.models.Candidate;
import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(String email);

    Candidate findCandidateByParty(Party party);

    Candidate findCandidateByPartyAndElection(Party party, Election election);
}
