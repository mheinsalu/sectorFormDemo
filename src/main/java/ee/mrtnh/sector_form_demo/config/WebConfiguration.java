package ee.mrtnh.sector_form_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
@Slf4j
public class WebConfiguration implements ServletContextInitializer {

    // Class from https://dba-presents.com/index.php/jvm/java/87-enabling-h2-db-in-spring-boot-app

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initH2Console(servletContext);
    }

    private void initH2Console(ServletContext servletContext) {
        log.info("Starting H2 console");
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.setLoadOnStartup(1);
        log.info("Started H2 console");
    }
}
