package gr.aueb.cf.customermanagmentapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class CustomerManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManagmentApplication.class, args);
    }



}
