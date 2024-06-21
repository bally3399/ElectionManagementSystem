package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String voterNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String stateOfOrigin;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Vote> voteHistory = new ArrayList<>();
    @Embedded
    private Address address;
    private boolean isLocked;

}
