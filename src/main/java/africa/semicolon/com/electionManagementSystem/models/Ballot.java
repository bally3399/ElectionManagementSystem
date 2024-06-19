package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "ballot")
public class Ballot {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long ballotId;
    private Long electionId;
    @OneToMany
    private List<Vote> votes;
}
