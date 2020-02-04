package ee.mrtnh.sector_form_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Sectors")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Sector implements Comparable<Sector> {
    /*
     * @Data is a convenient shortcut annotation that bundles the features of
     * @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together.
     */

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Integer value;
    private int level;
    private String parentSector;

    @Override
    public int compareTo(Sector otherSector) {
        return this.getId().compareTo(otherSector.getId());
    }
}
