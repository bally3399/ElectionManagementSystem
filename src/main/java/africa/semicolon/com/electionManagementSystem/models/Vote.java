package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    @OneToOne
    private Candidate candidate;
    @OneToOne
    private Voter voter;
    @OneToOne
    private Election election;
}
