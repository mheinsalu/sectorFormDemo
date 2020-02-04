package ee.mrtnh.sector_form_demo.service;

import ee.mrtnh.sector_form_demo.model.Sector;
import ee.mrtnh.sector_form_demo.model.UserInfo;
import ee.mrtnh.sector_form_demo.model.UserInfoJson;
import ee.mrtnh.sector_form_demo.repository.SectorRepository;
import ee.mrtnh.sector_form_demo.repository.UserInfoRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class SectorFormDemoServiceTest {

    // TODO: these unit tests

    @MockBean
    private SectorRepository sectorRepository;

    @MockBean
    private UserInfoRepository userInfoRepository;

    @InjectMocks
    SectorFormDemoService sectorFormDemoService;

    @Before
    public void init() {
        java.util.List<Sector> selectedSectors = new ArrayList<>();
        selectedSectors.add(new Sector());
        List<Sector> notSelectedSectors = new ArrayList<>();
        notSelectedSectors.add(new Sector());
        UserInfoJson userInfoJson = new UserInfoJson("userName", "19492a42-3bc7-49da-9484-7c952481f0f5", selectedSectors, notSelectedSectors, "yes");
        UserInfo userInfo = new UserInfo("userName", "19492a42-3bc7-49da-9484-7c952481f0f5", "1,2,3", "yes");

        when(userInfoRepository.findUserInfoByUserId("19492a42-3bc7-49da-9484-7c952481f0f5")).thenReturn(userInfo);
        when(sectorRepository.findAll()).thenReturn(selectedSectors);
    }

    @Test
    void checkCookieValueAndGenerateNewIfNeeded() {

    }

    @Test
    void saveUserInfo() {

    }

    @Test
    void getUserInfoJson() {

    }

    @Test
    void convertUserInfoToUserInfoJson() {

    }
}