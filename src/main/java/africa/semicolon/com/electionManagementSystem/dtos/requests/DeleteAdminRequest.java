package africa.semicolon.com.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteAdminRequest {
    private String email;

    public DeleteAdminRequest(long l) {
    }

    public DeleteAdminRequest() {

    }
}
