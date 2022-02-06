
package com.findmyboat.boatmanagement.persistence;

import com.findmyboat.boatmanagement.model.BoatView;

import java.util.List;

public interface BoatDAO
{

    public List<BoatView> getBoats();

    public BoatView getBoat(String id);

    public boolean deleteBoat(String id);

    public String addBoat(String name, String description);

    public boolean updateBoat(String id, String name, String description);

}
