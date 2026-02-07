package com.gatekeeper.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(crsf -> crsf.disable())

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/ui/**").authenticated()
                                .anyRequest().permitAll()
                )

                .formLogin(form ->
                        form.loginPage("/login")
                                .defaultSuccessUrl("/ui/list-apis" , true)
                                .failureUrl("/login?error")
                                .permitAll()
                )


                .logout(logut ->
                        logut.logoutSuccessUrl("/login")
                );

       return  http.build() ;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }




}
