package com.melo.kc_appointments.adapters.controllers;

import com.melo.kc_appointments.domain.Event;
import com.melo.kc_appointments.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    public EventServices eventServices;

    @PostMapping(value = "/addEvent")
    public String addEvent(@RequestBody Event event){
        eventServices.addEvent(event);
        return "ADDED!";
    }

    @GetMapping(value = "/getEvents")
    public List<Event> getEvents(){
        return eventServices.getEvents();
    }

    @GetMapping(value = "/getAvailableTimes")
    public List<Event> getByBeginningDate(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date){
        return eventServices.getAvailableTimes(date);
    }

}
