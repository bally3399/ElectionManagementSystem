package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String dateOfBirth;
    private String address;

}
