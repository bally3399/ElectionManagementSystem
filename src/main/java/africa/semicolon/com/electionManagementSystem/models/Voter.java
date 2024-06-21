package africa.semicolon.com.electionManagementSystem.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
import java.time.LocalDate;
=======

>>>>>>> chichi
import java.util.List;


import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "Voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
<<<<<<< HEAD
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
=======
    private String password;
    private String dateOfBirth;
>>>>>>> chichi
    @OneToMany(fetch = FetchType.EAGER)
    private List<Vote> voteHistory;
    @Embedded
    private Address address;
    private boolean isSuspended;
    private boolean isLocked;
}
