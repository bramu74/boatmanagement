
package com.findmyboat.boatmanagement.persistence;

import com.findmyboat.boatmanagement.model.UserView;

public interface UserDAO
{

    public UserView getUser(String login);

}
