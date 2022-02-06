package com.findmyboat.boatmanagement.api;

import com.findmyboat.boatmanagement.exception.NoSuchBoatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public final class APIExceptionHandler
{
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchBoatException.class)
    public void handleConflict()
    {
        // Nothing to do
    }
}
