package africa.semicolon.com.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name = "Elections")
public class Election {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long electionId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Category category;
}
