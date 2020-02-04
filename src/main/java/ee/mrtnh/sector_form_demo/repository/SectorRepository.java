package ee.mrtnh.sector_form_demo.repository;


import ee.mrtnh.sector_form_demo.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional // any failure causes the entire operation to roll back to its previous state and to re-throw the original exception
public interface SectorRepository extends JpaRepository<Sector, Integer> {

    Sector findSectorByName(String name);

    Sector findSectorByValue(Integer value);
}
