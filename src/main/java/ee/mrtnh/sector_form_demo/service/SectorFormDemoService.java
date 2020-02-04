package ee.mrtnh.sector_form_demo.service;

import ee.mrtnh.sector_form_demo.model.Sector;
import ee.mrtnh.sector_form_demo.model.UserInfo;
import ee.mrtnh.sector_form_demo.model.UserInfoJson;
import ee.mrtnh.sector_form_demo.repository.SectorRepository;
import ee.mrtnh.sector_form_demo.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.UUID.randomUUID;

@Service
@Slf4j
public class SectorFormDemoService {

    // TODO: split into smaller Service classes?

    @Resource
    UserInfoRepository userInfoRepository;

    @Resource
    SectorRepository sectorRepository;

    public void checkCookieValueAndGenerateNewIfNeeded(String userIdCookie, HttpServletResponse response) {
        if (userIdCookie.compareTo("noCookieSet") == 0) {
            log.info("No cookies found. Generating new userId for user and setting it as a cookie.");
            String userId = randomUUID().toString();
            log.info("Generated userId is " + userId);
            response.addCookie(new Cookie("userIdCookie", userId));
        } else {
            log.info("Found existing userIdCookie. Welcome back, user " + userIdCookie);
        }
    }

    public void saveUserInfo(UserInfo userInfo, String userIdCookie) {
        userInfo.setUserId(userIdCookie);
        log.info("Saving info of user with ID " + userInfo.getUserId() + " to database.");
        // check if user exists in db. set userInfo's ID manually to perform UPDATE operation with save() method
        if (userInfoRepository.existsByUserId(userIdCookie)) {
            log.info("User with ID " + userInfo.getUserId() + " already exists in database. Updating data.");
            Integer id = userInfoRepository.findUserInfoByUserId(userIdCookie).getId();
            userInfo.setId(id);
        }
        userInfoRepository.save(userInfo);
        log.info("Saved info of user with ID " + userInfo.getUserId() + " to database.");
    }

    private List<Sector> convertSectorsStringToSectorsList(String parsedSectorsString) {
        String[] listOfSectorValues = parsedSectorsString.split(",");
        List<Sector> listOfSectors = new ArrayList<>();
        for (String sectorValue : listOfSectorValues) {
            Sector sector = sectorRepository.findSectorByValue(Integer.valueOf(sectorValue));
            listOfSectors.add(sector);
            Collections.sort(listOfSectors); // sort by table ID in ascending order so that Sectors would be in correct order
        }
        return listOfSectors;
    }

    private List<Sector> getListOfNotSelectedSectors(List<Sector> listOfSelectedSectors) {
        List<Sector> listOfAllSectors = sectorRepository.findAll();
        listOfAllSectors.removeAll(listOfSelectedSectors);
        return listOfAllSectors;
    }

    public UserInfoJson getUserInfoJson(String userId) {
        log.info("Getting info of user with ID " + userId);
        UserInfo userInfoFromDatabase = userInfoRepository.findUserInfoByUserId(userId);
        if (userInfoFromDatabase == null) {
            log.info("User with ID " + userId + " doesn't exist in database. Returning default form data.");
            return createDefaultUserInfoJson();
        } else {
            log.info("Found user with ID " + userId + " in database. Returning their form data.");
            return convertUserInfoToUserInfoJson(userInfoFromDatabase);
        }
    }

    private UserInfoJson createDefaultUserInfoJson() {
        return new UserInfoJson("", "", null, sectorRepository.findAll(), "");
    }

    public UserInfoJson convertUserInfoToUserInfoJson(UserInfo userInfoFromDatabase) {
        UserInfoJson userInfoJson = new UserInfoJson();
        userInfoJson.setUserName(userInfoFromDatabase.getUserName());
        userInfoJson.setUserId(userInfoFromDatabase.getUserId());
        userInfoJson.setAgreeToTerms(userInfoFromDatabase.getAgreeToTerms());

        // UserInfo is saved to DB with parsed selectedSectorsString
        List<Sector> selectedSectorsList = convertSectorsStringToSectorsList(userInfoFromDatabase.getSelectedSectors());
        userInfoJson.setSelectedSectors(selectedSectorsList);
        userInfoJson.setNotSelectedSectors(getListOfNotSelectedSectors(selectedSectorsList));
        return userInfoJson;
    }
}
