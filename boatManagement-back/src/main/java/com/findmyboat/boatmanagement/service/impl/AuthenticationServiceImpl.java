package com.findmyboat.boatmanagement.service.impl;

import com.findmyboat.boatmanagement.model.UserView;
import com.findmyboat.boatmanagement.persistence.UserDAO;
import com.findmyboat.boatmanagement.persistence.UserTokenDAO;
import com.findmyboat.boatmanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{

    protected static SecureRandom random = new SecureRandom();
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserTokenDAO userTokenDAO;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String username, String password)
    {
        UserView user = userDAO.getUser(username);

        if (user == null)
            throw new BadCredentialsException("Bad credentials");

        if (passwordEncoder.matches(password, user.getEncryptedPassword()))
        {
            String token = generateToken();
            userTokenDAO.registerTokenForUser(token, username);
            return token;
        }

        throw new BadCredentialsException("Bad credentials");
    }

    public void logout(String username)
    {
        userTokenDAO.deleteTokenForUser(username);
    }

    @Override
    public UserView getUserFromToken(String token)
    {
        String username = userTokenDAO.getUsernameFromToken(token);

        if (username == null)
            throw new BadCredentialsException("Bad credentials");

        UserView matchingUser = userDAO.getUser(username);

        if (matchingUser == null)
            throw new BadCredentialsException("Bad credentials");

        return matchingUser;
    }

    private String generateToken()
    {
        long longToken = Math.abs(random.nextLong());
        return Long.toString(longToken, 16);
    }
}
