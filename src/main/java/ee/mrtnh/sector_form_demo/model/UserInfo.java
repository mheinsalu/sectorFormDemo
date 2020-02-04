package ee.mrtnh.sector_form_demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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

    String userName;
    String userId;
    @Lob
    String selectedSectors; // Can't save List<> to table. String is VARCHAR(255) in table and too short.
    String agreeToTerms;

    public UserInfo(String userName, String userId, String selectedSectors, String agreeToTerms) {
        this.userName = userName;
        this.userId = userId;
        this.selectedSectors = selectedSectors;
        this.agreeToTerms = agreeToTerms;
    }
}
