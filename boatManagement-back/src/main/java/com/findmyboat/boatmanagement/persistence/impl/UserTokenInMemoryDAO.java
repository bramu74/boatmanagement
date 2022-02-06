
package com.findmyboat.boatmanagement.persistence.impl;

import com.findmyboat.boatmanagement.persistence.UserTokenDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserTokenInMemoryDAO implements UserTokenDAO
{

    private Map<String, String> tokenPerUserStorage = new HashMap<>();

    @Override
    public void registerTokenForUser(String token, String username)
    {
        deleteTokenForUser(username);
        tokenPerUserStorage.put(token, username);
    }

    @Override
    public String getUsernameFromToken(String token)
    {
        return tokenPerUserStorage.get(token);
    }

    @Override
    public boolean deleteTokenForUser(String username)
    {
        if (!tokenPerUserStorage.containsValue(username))
            return false;

        String matchingToken = null;

        for (Map.Entry entry : tokenPerUserStorage.entrySet())
        {
            if (entry.getValue().equals(username))
            {
                matchingToken = (String) entry.getKey();
                break;
            }
        }

        if (matchingToken != null)
        {
            tokenPerUserStorage.remove(matchingToken);
            return true;
        }

        return false;
    }
}
