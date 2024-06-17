package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Admin {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
}
