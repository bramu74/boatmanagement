
package com.findmyboat.boatmanagement.security;

import com.findmyboat.boatmanagement.model.UserView;
import com.findmyboat.boatmanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        // nothing to do
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        if (authentication == null)
            throw new BadCredentialsException("Bad credentials");

        UserView user = authenticationService.getUserFromToken((String) authentication.getCredentials());
        return User.withUsername(user.getName())
               .password(user.getEncryptedPassword())
               .roles(user.getRole())
               .build();
    }
}
