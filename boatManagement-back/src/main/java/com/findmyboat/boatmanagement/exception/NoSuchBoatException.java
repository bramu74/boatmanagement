
package com.findmyboat.boatmanagement.exception;

public final class NoSuchBoatException extends Exception
{
    private String boatId;

    public NoSuchBoatException(String boatId)
    {
        this.boatId = boatId;
    }
}
