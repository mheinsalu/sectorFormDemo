package ee.mrtnh.sector_form_demo.repository;


import ee.mrtnh.sector_form_demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    // method body is auto-generated.
    // Parameter name in method name must match parameter name in class
    UserInfo findUserInfoByUserId(String userId);

    boolean existsByUserId(String userId);
}
