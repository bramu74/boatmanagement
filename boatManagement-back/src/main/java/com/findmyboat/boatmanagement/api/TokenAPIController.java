package com.findmyboat.boatmanagement.api;

import com.findmyboat.boatmanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenAPIController
{

    @Autowired
    private AuthenticationService tokenService;

    @PostMapping("/token")
    public TokenResult getToken(@RequestParam(value = "login") String login,
                                @RequestParam(value = "password") String password)
    {
        return new TokenResult(tokenService.login(login, password));
    }

    @PostMapping("/api/logout")
    public void logout()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
        {
            tokenService.logout(auth.getName());
        }
    }

    private class TokenResult
    {
        public String authorizationBearerToken;

        public TokenResult(String authorizationBearerToken)
        {
            this.authorizationBearerToken = authorizationBearerToken;
        }
    }

}
