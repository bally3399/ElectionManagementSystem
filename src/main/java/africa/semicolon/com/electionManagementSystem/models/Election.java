package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Election {
    @Id
    private Long electionId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Category category;
}
