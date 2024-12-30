package com.formsapp.util;

import com.formsapp.exception.FormException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class LoggedInUserUtils {
    /**
     * Retrieves the Email of the currently logged-in user.
     *
     * @return the Email or null if not authenticated.
     */
    public static String getLoggedInUserEmail() throws FormException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaimAsString("email");
        }
        throw new FormException("logged in user details not found");
    }
}
