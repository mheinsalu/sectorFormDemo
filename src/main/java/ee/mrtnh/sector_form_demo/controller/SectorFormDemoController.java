package ee.mrtnh.sector_form_demo.controller;

import ee.mrtnh.sector_form_demo.model.UserInfo;
import ee.mrtnh.sector_form_demo.model.UserInfoJson;
import ee.mrtnh.sector_form_demo.service.SectorFormDemoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static java.util.UUID.randomUUID;

@Controller
@Slf4j
public class SectorFormDemoController {

    private static final String HOME_VIEW = "index";

    @Resource
    SectorFormDemoService sectorFormDemoService;

    // @GetMapping(value = "/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(@CookieValue(value = "userIdCookie", defaultValue = "noCookieSet") String userIdCookie, HttpServletResponse response) {
        log.info("Loading home page");
        sectorFormDemoService.checkCookieValueAndGenerateNewIfNeeded(userIdCookie, response);
        log.info("Loaded home page");
        return HOME_VIEW;
    }

    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    // on external Tomcat the url is <artifact name> + value
    @ResponseBody
    // The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.
    public void saveUserInfoUsingJson(@RequestBody UserInfo userInfo, @CookieValue(value = "userIdCookie", defaultValue = "noCookieSet") String userIdCookie) {
        log.info("Called saveUserInfoUsingJson");
        sectorFormDemoService.saveUserInfo(userInfo, userIdCookie);
    }

    /*
    GET carries request parameter appended in URL string while POST carries request parameter in message body
        which makes it more secure way of transferring data from client to server in http protocol
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserInfoJson getUserInfoUsingJson(@CookieValue(value = "userIdCookie", defaultValue = "noCookieSet") String userIdCookie) {
        log.info("Called getUserInfoUsingJson");
        return sectorFormDemoService.getUserInfoJson(userIdCookie);
    }

}
