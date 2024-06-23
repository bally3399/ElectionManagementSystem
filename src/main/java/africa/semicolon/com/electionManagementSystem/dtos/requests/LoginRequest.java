package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String password;
    private String username;

    public Object getEmail() {
        return this.password;
    }
}
