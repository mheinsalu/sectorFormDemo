package ee.mrtnh.sector_form_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SectorFormDemoApplication extends SpringBootServletInitializer {
    // extends and override are needed to start as a .war without embedded Tomcat
    // spring initializer puts this in a separate class named ServletInitializer

    // TODO: api URL to controller and JS from .properties?
    // TODO: Spring Security, defend against attacks?
    // TODO: maybe split SectorFormDemoService into >=two smaller classes
    // TODO: unit tests
    public static void main(String[] args) {
        SpringApplication.run(SectorFormDemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SectorFormDemoApplication.class);
    }
}
