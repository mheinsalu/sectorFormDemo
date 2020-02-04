package ee.mrtnh.sector_form_demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.mrtnh.sector_form_demo.model.Sector;
import ee.mrtnh.sector_form_demo.model.UserInfo;
import ee.mrtnh.sector_form_demo.model.UserInfoJson;
import ee.mrtnh.sector_form_demo.service.SectorFormDemoService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SectorFormDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectorFormDemoService sectorFormDemoService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        /*
        java.util.List<Sector> selectedSectors = new ArrayList<>();
        selectedSectors.add(new Sector());
        List<Sector> notSelectedSectors = new ArrayList<>();
        notSelectedSectors.add(new Sector());

        UserInfoJson userInfoJson = new UserInfoJson("userName", "19492a42-3bc7-49da-9484-7c952481f0f5", selectedSectors, notSelectedSectors, "yes");
        when(sectorFormDemoService.getUserInfoJson("19492a42-3bc7-49da-9484-7c952481f0f5")).thenReturn(userInfoJson);
        */
    }

    @Test
    void showHome_ok() throws Exception {
        // test if endpoint is up
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void saveUserInfoUsingJson_ok() throws Exception {
        UserInfo userInfo = new UserInfo("userName", "19492a42-3bc7-49da-9484-7c952481f0f5", "1,2,3", "yes");
        mockMvc.perform(post("/saveUserInfo").content(mapper.writeValueAsString(userInfo)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getUserInfoUsingJson_ok() throws Exception {
        String userId = "19492a42-3bc7-49da-9484-7c952481f0f5";
        mockMvc.perform(get("/getUserInfo").content(mapper.writeValueAsString(userId)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}