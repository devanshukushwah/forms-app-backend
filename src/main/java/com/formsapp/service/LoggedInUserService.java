package com.formsapp.service;

import com.formsapp.exception.FormException;

public interface LoggedInUserService {
    /**
     * Retrieves the email of the currently logged-in user from the security context.
     * This method assumes that the authentication object contains a JWT (JSON Web Token) with an "email" claim.
     *
     * @return the email of the currently logged-in user
     * @throws FormException if the user is not authenticated or the email claim is not found
     */
    public String getLoggedInUserEmail() throws FormException;
}
