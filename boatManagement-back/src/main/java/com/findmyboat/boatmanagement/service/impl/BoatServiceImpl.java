package com.findmyboat.boatmanagement.service.impl;

import com.findmyboat.boatmanagement.exception.NoSuchBoatException;
import com.findmyboat.boatmanagement.model.BoatView;
import com.findmyboat.boatmanagement.persistence.BoatDAO;
import com.findmyboat.boatmanagement.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class BoatServiceImpl implements BoatService
{

    @Autowired
    private BoatDAO boatDAO;

    @Override
    public List<BoatView> getBoats()
    {
        return boatDAO.getBoats();
    }

    public BoatView getBoat(String id) throws NoSuchBoatException
    {
        BoatView matchingBoat = boatDAO.getBoat(id);

        if (matchingBoat == null)
            throw new NoSuchBoatException(id);

        return matchingBoat;
    }

    public void deleteBoat(String id) throws NoSuchBoatException
    {
        if (!boatDAO.deleteBoat(id))
            throw new NoSuchBoatException(id);
    }

    @Override
    public String addBoat(String name, String description)
    {
        return boatDAO.addBoat(name, description);
    }

    @Override
    public void updateBoat(String id, String name, String description) throws NoSuchBoatException
    {
        if (!boatDAO.updateBoat(id, name, description))
            throw new NoSuchBoatException(id);
    }
}
