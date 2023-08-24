package spring.springboot.middlewares;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import spring.springboot.constants.SecurityConstant;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfiguration {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    CustomAuthenEntryPoint customAuthenEntryPoint = new CustomAuthenEntryPoint();

    @Bean
    AuthenticationManager authManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CustomRequestHeaderToken customFilter() throws Exception {
        return new CustomRequestHeaderToken(authManager());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(header -> header.frameOptions(option -> option.disable()))
            .cors(cors -> cors.configurationSource(corsConfigurition())).csrf(crfs -> crfs.disable())
            .sessionManagement((ses) -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling((ex) -> ex.authenticationEntryPoint(customAuthenEntryPoint))
            .authorizeHttpRequests(
                (auth) -> auth
                    .requestMatchers(SecurityConstant.publicAuthen).permitAll()
                    .requestMatchers(SecurityConstant.swaggerDocs, SecurityConstant.swaggerResource, SecurityConstant.docsApi).permitAll()
                    .requestMatchers(SecurityConstant.commonAuthen).hasAnyAuthority(SecurityConstant.adminRole, SecurityConstant.userRole)
                    .requestMatchers(SecurityConstant.privateAdminAuthen).hasAnyAuthority(SecurityConstant.adminRole)
                    .requestMatchers(SecurityConstant.privateUserAuthen).hasAnyAuthority(SecurityConstant.userRole));
        http.addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurition() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Origin"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(
            List.of("Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
        );
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
