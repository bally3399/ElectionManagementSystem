package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Vote> voteHistory;
    @Embedded
    private Address address;
    private boolean isSuspended;
    private boolean isLocked;

}
