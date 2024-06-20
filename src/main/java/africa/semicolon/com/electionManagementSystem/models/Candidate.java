package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Party party;
    private String biography;
    private String phoneNumber;
    private String email;
    // position contested // or Enum
    private String position;
}
