package com.shin.ricu.config;

import com.shin.ricu.security.MemberDetailService;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.security.handler.CustomAccessDeniedHandler;
import com.shin.ricu.security.handler.LoginFailureHandler;
import com.shin.ricu.security.handler.LoginSuccessHandler;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final DataSource dataSource;
    private final MemberDetailService memberDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        log.info("-------------------------filter Chaining-----------------------");

        http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/gallery/board/write").authenticated()
                .requestMatchers("/gallery/board/modify").authenticated()
                .requestMatchers("/gallery/create").authenticated()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
        ).formLogin(formLogin -> formLogin
                .loginPage("/member/login").successHandler(authenticationSuccessHandler())
                        .failureHandler(loginFailureHandler()))
                .userDetailsService(memberDetailService)
                .exceptionHandling(e ->accessDeniedHandler()
                ).logout(a -> a.logoutUrl("/member/logout").
                        addLogoutHandler(((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            if(session != null)
                            {
                                session.invalidate();
                            }
                        })).logoutSuccessHandler(((request, response, authentication) -> {
                            response.sendRedirect("/member/login");
                        }))
                        .deleteCookies("remember-me"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {return new CustomAccessDeniedHandler();}

    @Bean
    public AuthenticationFailureHandler loginFailureHandler()
    {
        return new LoginFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler()
    {
        return new LoginSuccessHandler(passwordEncoder());
    }
}
