package africa.semicolon.com.electionManagementSystem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

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
    private String firstName;
    private String lastName;
    private String nationalIdentificationNumber;
    private String voterNumber;
    private String stateOfOrigin;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String email;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Vote> voteHistory = new ArrayList<>();
    @Embedded
    private Address address;
    private boolean isLocked;
}
