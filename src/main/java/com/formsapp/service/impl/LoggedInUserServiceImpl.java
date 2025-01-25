package com.formsapp.service.impl;

import com.formsapp.exception.FormException;
import com.formsapp.service.LoggedInUserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Profile("!local")
@Service
public class LoggedInUserServiceImpl implements LoggedInUserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggedInUserEmail() throws FormException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaimAsString("email");
        }
        throw new FormException("logged in user details not found");
    }
}
