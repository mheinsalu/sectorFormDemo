package ee.mrtnh.sector_form_demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// An entity represents a table stored in a database.
// Every instance of an entity represents a row in the table.
// Spring generates tables based on classes with @Entity
@Entity(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue
    private Integer id;

    // for security. see https://stackabuse.com/securing-spring-boot-web-applications/
    @NotBlank(message = "userName must not be empty")
    @Size(min = 1, max = 40)
    @Pattern(regexp = "|[a-zA-Z-]+", message = "Name must not include special characters.")
    String userName;

    @Size(min = 36, max = 36) // generated UUID. length is always 36 characters
    @Pattern(regexp = "[a-z0-9-]+", message = "userId must not include special characters.")
    String userId;

    @Lob
    @NotBlank(message = "selectedSectors must not be empty")
    @Size(min = 1)
    @Pattern(regexp = "([0-9]{1,3},{0,1})+", message = "userId must not include special characters.")
    String selectedSectors; // Can't save List<> to table. String is VARCHAR(255) in table and too short.

    @NotBlank(message = "agreeToTerms must not be empty")
    @Size(min = 2, max = 3) // yes or no
    @Pattern(regexp = "[a-z]+", message = "agreeToTerms must not include special characters.")
    String agreeToTerms;

    public UserInfo(String userName, String userId, String selectedSectors, String agreeToTerms) {
        this.userName = userName;
        this.userId = userId;
        this.selectedSectors = selectedSectors;
        this.agreeToTerms = agreeToTerms;
    }
}
