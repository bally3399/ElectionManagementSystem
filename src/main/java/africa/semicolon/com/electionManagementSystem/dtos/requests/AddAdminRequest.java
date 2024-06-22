package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddAdminRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public AddAdminRequest(String email, String password) {}

    public AddAdminRequest() {

    }
}
