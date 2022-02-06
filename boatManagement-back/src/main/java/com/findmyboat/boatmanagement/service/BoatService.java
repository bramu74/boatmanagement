package com.findmyboat.boatmanagement.service;

import com.findmyboat.boatmanagement.exception.NoSuchBoatException;
import com.findmyboat.boatmanagement.model.BoatView;

import java.util.List;

public interface BoatService
{

    public List<BoatView> getBoats();

    public BoatView getBoat(String id) throws NoSuchBoatException;

    public void deleteBoat(String id) throws NoSuchBoatException;

    public void updateBoat(String id, String name, String description) throws NoSuchBoatException;

    public String addBoat(String name, String description);

}
