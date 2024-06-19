package africa.semicolon.com.electionManagementSystem.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    private String buildingNumber;
    private String ward;
    private String localGovernmentArea;
    private String city;
    private String state;
}
