package africa.semicolon.com.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteAdminResponse {
    private String message;

    public DeleteAdminResponse(String adminDeletedSuccessfully) {
    }
}
