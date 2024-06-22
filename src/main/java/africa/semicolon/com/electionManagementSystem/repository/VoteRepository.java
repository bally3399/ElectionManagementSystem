package africa.semicolon.com.electionManagementSystem.repository;

import africa.semicolon.com.electionManagementSystem.models.Candidate;
import africa.semicolon.com.electionManagementSystem.models.Category;
import africa.semicolon.com.electionManagementSystem.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findVoteByCandidate(Long candidateId);

}
