package com.findmyboat.boatmanagement.model.impl;

import com.findmyboat.boatmanagement.model.BoatView;

public final class Boat extends BoatBase implements BoatView
{

    private String id;

    public Boat(String id, String name, String description)
    {
        super(name, description);
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
