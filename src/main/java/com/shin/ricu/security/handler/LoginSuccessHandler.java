package com.shin.ricu.security.handler;

import com.shin.ricu.security.dto.MemberSecurityDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.server.header.CrossOriginEmbedderPolicyServerHttpHeadersWriter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Log4j2
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        log.info("Login Success!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("LoginSuccessHandler onAuthenticationSuccess..........................");
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO)authentication.getPrincipal();
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        log.info("Logined + " + prevPage);
        if(prevPage == null) redirectStrategy.sendRedirect(request, response,"/member/info?Member=" + memberSecurityDTO.getMemberID());
        else redirectStrategy.sendRedirect(request, response, prevPage);
    }
}
