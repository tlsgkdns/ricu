package com.shin.ricu.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException
    {
        String errorMessage;

        if(exception instanceof BadCredentialsException) {
            errorMessage = "ID and Password is Unmatched";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "Cannot Process the Login";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "This Username is not Exist";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = "Authentication Denied!";
        } else {
            errorMessage = "Unknown Error";
        }
        FlashMap flashMap = new FlashMap();
        flashMap.put("error", errorMessage);
        FlashMapManager flashMapManager = new SessionFlashMapManager();
        flashMapManager.saveOutputFlashMap(flashMap, request, response);
        response.sendRedirect("/member/login");
    }
}
