package africa.semicolon.com.electionManagementSystem.dtos.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewVoterRequest {
    private String email;
    private String password;
}
