
package com.findmyboat.boatmanagement.persistence.impl;

import com.findmyboat.boatmanagement.model.UserRole;
import com.findmyboat.boatmanagement.model.UserView;
import com.findmyboat.boatmanagement.model.impl.User;
import com.findmyboat.boatmanagement.persistence.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;

@Repository
public final class UserInMemoryDAO implements UserDAO
{

    private Map<String, UserView> userStorage = new HashMap<>();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserView getUser(String login)
    {
        return userStorage.get(login);
    }

    @PostConstruct
    public void init()
    {
        userStorage.put("admin", new User("admin", passwordEncoder.encode("passwordA"), UserRole.ADMIN));
        userStorage.put("user", new User("user", passwordEncoder.encode("passwordB"), UserRole.USER));
    }
}
