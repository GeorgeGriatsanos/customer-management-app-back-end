package gr.aueb.cf.customermanagmentapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/customer/").permitAll()
//                        .requestMatchers("/api/user/").permitAll()
//                        .requestMatchers("/api/customer/all").permitAll()
//                        .requestMatchers("/api/customer/delete/{id}").permitAll()
//                        .requestMatchers("/api/customer/add").permitAll()
//                        .requestMatchers("/api/customer/update").permitAll()
//                        .requestMatchers("/api/user/register").permitAll()
//                        .requestMatchers("/api/user/login").permitAll()// Allow public access to /save endpoint
//                        .anyRequest().authenticated() // Require authentication for other endpoints
//                )
//                .httpBasic(withDefaults()); // Enable basic authentication with defaults

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
