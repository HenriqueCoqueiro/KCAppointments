package com.melo.kc_appointments.adapters.controllers;

import com.melo.kc_appointments.domain.Event;
import com.melo.kc_appointments.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @DeleteMapping(value = "/deleteEvent")
    public String deleteEvent(@RequestParam String name){
        eventServices.deleteEvent(name);
        return "DELETED!";
    }

    @GetMapping(value = "/getEvent")
    public Event getEvent(@RequestParam String name){
        return eventServices.getEvent(name);
    }

    @GetMapping(value = "/getAllEvents")
    public List<Event> getAllEvents(){
        return eventServices.getAllEvents();
    }

    @GetMapping(value = "/getScheduledEvents")
    public List<Event> getScheduledEvents(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date){
        return eventServices.getScheduledEvents(date);
    }

    @GetMapping(value = "/getAvailableDateTime")
    public List<LocalDateTime> getAvailableDateTime(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date){
        List<Event> events = eventServices.getScheduledEvents(date);
        List<LocalDateTime> scheduledDateTime = new ArrayList<>();
        for(int i = 0; i < events.size(); i++){
            scheduledDateTime.add(events.get(i).getBeginningDate());
            scheduledDateTime.add(events.get(i).getEndingDate());
        }
        return eventServices.getAvailableDateTime(scheduledDateTime, date);

    }

}
