package com.findmyboat.boatmanagement.model.impl;

import com.findmyboat.boatmanagement.model.UserView;

public final class User implements UserView
{

    private String name;
    private String encryptedPassword;
    private String role;

    public User(String name, String encryptedPassword, String role)
    {
        this.name = name;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getEncryptedPassword()
    {
        return encryptedPassword;
    }

    @Override
    public String getRole()
    {
        return role;
    }
}
