package dev.marksduarte.springbootbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseApplication.class, args);
    }

}
