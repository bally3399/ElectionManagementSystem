package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class Ballot {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long ballotId;
    private Long electionId;
    private Long voterId;
    @OneToMany
    private List<Voter> voterList;
}
