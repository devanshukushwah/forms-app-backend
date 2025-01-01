package com.formsapp.util;

import com.formsapp.exception.FormException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class LoggedInUserUtils {

    /**
     * Retrieves the email of the currently logged-in user from the security context.
     * This method assumes that the authentication object contains a JWT (JSON Web Token) with an "email" claim.
     *
     * @return the email of the currently logged-in user
     * @throws FormException if the user is not authenticated or the email claim is not found
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
