package ee.mrtnh.sector_form_demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// basically a wrapper for UserInfo for easier JSON parsing in JavaScript
@Getter
@Setter
@NoArgsConstructor
public class UserInfoJson {

    String userName;
    String userId;
    List<Sector> selectedSectors; // easier to parse in JavaScript than UserInfo's String
    List<Sector> notSelectedSectors;
    String agreeToTerms;

    public UserInfoJson(String userName, String userId, List<Sector> selectedSectors, List<Sector> notSelectedSectors, String agreeToTerms) {
        this.userName = userName;
        this.userId = userId;
        this.selectedSectors = selectedSectors;
        this.notSelectedSectors = notSelectedSectors;
        this.agreeToTerms = agreeToTerms;
    }
}
