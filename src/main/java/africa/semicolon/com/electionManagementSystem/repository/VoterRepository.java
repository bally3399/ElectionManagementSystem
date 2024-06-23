package africa.semicolon.com.electionManagementSystem.repository;

import africa.semicolon.com.electionManagementSystem.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Voter findByEmailAndPhoneNumber(String email, String phone);
    Voter findByNationalIdentificationNumber(String nationalIdentificationNumber);

    Voter findByEmailAndPassword(String email, String password);

    Voter findByVoterNumber(String voterNumber);
}
