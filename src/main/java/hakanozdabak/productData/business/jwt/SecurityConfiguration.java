package hakanozdabak.productData.business.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static hakanozdabak.productData.entities.concretes.Permission.*;
import static hakanozdabak.productData.entities.concretes.Role.ADMIN;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] AUTH_WHITELIST = {

            // for Swagger UI v2
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",

            // for Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/auth/**"

    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      http
              .csrf(AbstractHttpConfigurer::disable)
              .authorizeHttpRequests(auth->{
                  auth.requestMatchers(AUTH_WHITELIST).permitAll()
                          .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                          .requestMatchers("/api/v1/admin/**").hasAuthority(ADMIN.name())
                          .requestMatchers(GET,"/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                          .requestMatchers(POST,"/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                          .requestMatchers(PUT,"/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                          .requestMatchers(DELETE,"/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

                          .anyRequest()
                          .authenticated();
              })
              .sessionManagement(session->
                  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authenticationProvider(authenticationProvider)
              .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);




     return http.build();

    }
}
