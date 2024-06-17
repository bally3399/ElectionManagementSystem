package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Ballot {
    private Long ballotId;
    private Long electionId;
    private Long voterId;
    @OneToOne
    private List<Voter> voterList;
}
