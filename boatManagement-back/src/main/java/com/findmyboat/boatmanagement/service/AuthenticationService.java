package com.findmyboat.boatmanagement.service;

import com.findmyboat.boatmanagement.model.UserView;

public interface AuthenticationService
{

    public String login(String username, String password);

    public void logout(String username);

    public UserView getUserFromToken(String token);

}
