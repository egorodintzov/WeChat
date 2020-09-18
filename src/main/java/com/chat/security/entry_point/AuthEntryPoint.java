package com.chat.security.entry_point;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = Logger.getLogger(AuthEntryPoint.class.getName());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.severe("Unauthorized");
        response.sendError(401,"Unauthorized");
    }
}
