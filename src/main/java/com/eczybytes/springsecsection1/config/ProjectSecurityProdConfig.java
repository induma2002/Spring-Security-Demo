package com.eczybytes.springsecsection1.config;

import com.eczybytes.springsecsection1.exceptionhandling.CustomAccessDeniedHandler;
import com.eczybytes.springsecsection1.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
public class ProjectSecurityProdConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(sessionConfig -> sessionConfig.invalidSessionUrl("/reLogin"))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/balance","/cards").authenticated()
                .requestMatchers("/Contact","/notes","/register","/reLogin","/loginError").permitAll()
                .requestMatchers("/", "/favicon.ico", "/error", "/webjars/**", "/css/**", "/js/**", "/images/**").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc
                .authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

/*    @Bean
    CompromisedPasswordChecker passwordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }*/
}
