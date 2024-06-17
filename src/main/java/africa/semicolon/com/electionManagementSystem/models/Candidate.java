package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Candidate {
    @Id
    private Long id;
    private String name;
    private Party party;
}
