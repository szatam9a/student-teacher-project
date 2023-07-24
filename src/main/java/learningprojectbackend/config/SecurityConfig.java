package learningprojectbackend.config;

import learningprojectbackend.service.JpaUserDetailsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JpaUserDetailsManager jpaUserDetailsManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return
//                httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                        .authorizeHttpRequests(auth -> {
//                            auth.requestMatchers("/").permitAll();
//                            auth.requestMatchers("/user").hasRole("USER");
//                            auth.requestMatchers("/admin").hasRole("ADMIN");
//                            auth.anyRequest().authenticated();
//                        })
//                        .formLogin(Customizer.withDefaults())
//                        .build();
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/user/**").hasRole("ADMIN")
                                .requestMatchers("/api/example/**").hasRole("USER")
                                .requestMatchers("/api/registration").permitAll()
                                .requestMatchers("/login", "/logout").permitAll()
                                .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailsManager)
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
