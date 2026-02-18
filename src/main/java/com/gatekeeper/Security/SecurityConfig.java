package com.gatekeeper.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(crsf -> crsf.disable())

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/ui/manage-apis").hasRole("ADMIN")
                                .requestMatchers("/ui/**").authenticated()
                                .requestMatchers("/gateway/**" , "/login" , "/signup" , "/admin-signup").permitAll()
                                .anyRequest().permitAll()
                )

                .formLogin(form ->
                        form.loginPage("/login")
                                .defaultSuccessUrl("/ui/list-api" , true)
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
