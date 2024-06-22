package africa.semicolon.com.electionManagementSystem.dtos.requests;

import africa.semicolon.com.electionManagementSystem.models.Address;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class RegisterVoterRequest {
    private Long adminId;
    private String firstName;
    private String lastName;
    private Address address;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String message;
}
