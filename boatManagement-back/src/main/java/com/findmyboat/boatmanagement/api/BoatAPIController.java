package com.findmyboat.boatmanagement.api;

import com.findmyboat.boatmanagement.exception.NoSuchBoatException;
import com.findmyboat.boatmanagement.model.BoatView;
import com.findmyboat.boatmanagement.model.impl.BoatBase;
import com.findmyboat.boatmanagement.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoatAPIController
{

    @Autowired
    private BoatService boatService;

    @GetMapping("/boats")
    public List<BoatView> getBoats()
    {
        return boatService.getBoats();
    }

    @PostMapping("/boats")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public String addBoat(@Valid @RequestBody BoatBase boatBase)
    {
        return boatService.addBoat(boatBase.getName(), boatBase.getDescription());
    }

    @GetMapping("/boats/{boatId}")
    public BoatView getBoat(@PathVariable("boatId") String boatId) throws NoSuchBoatException
    {
        return boatService.getBoat(boatId);
    }

    @PutMapping("/boats/{boatId}")
    @Secured("ROLE_ADMIN")
    public void updateBoat(@PathVariable("boatId") String boatId, @Valid @RequestBody BoatBase boatBase) throws NoSuchBoatException
    {
        boatService.updateBoat(boatId, boatBase.getName(), boatBase.getDescription());
    }

    @DeleteMapping("/boats/{boatId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteBoat(@PathVariable("boatId") String boatId) throws NoSuchBoatException
    {
        boatService.deleteBoat(boatId);
    }

}
