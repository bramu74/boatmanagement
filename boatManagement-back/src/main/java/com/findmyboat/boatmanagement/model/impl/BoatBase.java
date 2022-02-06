package com.findmyboat.boatmanagement.model.impl;

import javax.validation.constraints.NotBlank;

public class BoatBase
{

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;

    public BoatBase(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
