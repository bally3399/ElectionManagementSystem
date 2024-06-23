package africa.semicolon.com.electionManagementSystem.repository;

import africa.semicolon.com.electionManagementSystem.models.Election;
import africa.semicolon.com.electionManagementSystem.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
