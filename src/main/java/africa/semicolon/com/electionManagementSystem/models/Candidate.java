package africa.semicolon.com.electionManagementSystem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String biography;

    private String phoneNumber;
    private String email;

    @Column(nullable = false)
    private String positionContested;

    @Column(nullable = false)
    private boolean isSuspended;

    public Candidate(String email, String name, LocalDate now) {

    }

    public Candidate() {

    }
}
