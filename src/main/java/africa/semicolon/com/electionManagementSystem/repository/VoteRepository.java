package africa.semicolon.com.electionManagementSystem.repository;

import africa.semicolon.com.electionManagementSystem.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
//    List<Vote> getAllVote();

    //Vote findVoteByCandidate(Long candidateId);

}
