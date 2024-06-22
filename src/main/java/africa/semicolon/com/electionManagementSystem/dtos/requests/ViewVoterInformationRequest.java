package africa.semicolon.com.electionManagementSystem.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
public class ViewVoterInformationRequest {

    private String firstName;
    private String lastName;
    private String voterNumber;
    private String stateOfOrigin;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
}
