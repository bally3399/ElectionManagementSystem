package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private Party party;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String biography;

    private String phoneNumber;
    private String email;

    @Column(nullable = false)
    private String positionContested;

    @Column(nullable = false)
    private boolean isSuspended;
}
