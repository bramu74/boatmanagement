
package com.findmyboat.boatmanagement.persistence.impl;

import com.findmyboat.boatmanagement.model.BoatView;
import com.findmyboat.boatmanagement.model.impl.Boat;
import com.findmyboat.boatmanagement.persistence.BoatDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public final class BoatInMemoryDAO implements BoatDAO
{
    private Map<String, BoatView> boatStorage = new HashMap<>();
    private int nextBoatId = 1;

    public List<BoatView> getBoats()
    {
        return new ArrayList<>(boatStorage.values());
    }

    public String addBoat(String name, String description)
    {
        String boatId = Integer.toString(nextBoatId++);
        boatStorage.put(boatId, new Boat(boatId, name, description));
        return boatId;
    }

    public BoatView getBoat(String id)
    {
        return boatStorage.get(id);
    }

    public boolean updateBoat(String id, String name, String description)
    {
        BoatView updatedBoat = boatStorage.replace(id, new Boat(id, name, description));
        return updatedBoat != null;
    }

    public boolean deleteBoat(String id)
    {
        BoatView removedBoat = boatStorage.remove(id);
        return removedBoat != null;
    }

    @PostConstruct
    public void init()
    {
        boatStorage.put("1", new Boat("1", "altara", "altara is an amazing catamaran"));
        boatStorage.put("2", new Boat("2", "evoq", "evoq is a little simple boat"));
        nextBoatId = 3;
    }
}
