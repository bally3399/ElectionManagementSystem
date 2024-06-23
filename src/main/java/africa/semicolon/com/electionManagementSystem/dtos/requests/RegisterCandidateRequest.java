package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.Party;
import africa.semicolon.com.electionManagementSystem.models.PositionContested;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterCandidateRequest {
    private Long adminId;
    private String firstName;
    private String lastName;
    private Party party;
    private LocalDate dateOfBirth;
    private String biography;
    private String phoneNumber;
    private String email;
    private PositionContested positionContested;

}
