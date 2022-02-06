
package com.findmyboat.boatmanagement.persistence;

public interface UserTokenDAO
{

    public void registerTokenForUser(String token, String username);

    public String getUsernameFromToken(String token);

    public boolean deleteTokenForUser(String username);

}
