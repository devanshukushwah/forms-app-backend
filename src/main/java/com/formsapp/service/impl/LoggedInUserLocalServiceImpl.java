package com.formsapp.service.impl;

import com.formsapp.exception.FormException;
import com.formsapp.service.LoggedInUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Profile("local")
@Service
public class LoggedInUserLocalServiceImpl implements LoggedInUserService {

    @Value("${app.local.email}")
    private String email;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggedInUserEmail() throws FormException {
        return email;
    }
}
