package com.melo.kc_appointments.services;

import com.melo.kc_appointments.adapters.repositories.EventRepo;
import com.melo.kc_appointments.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServices {

    @Autowired
    public EventRepo eventRepo;

    public void addEvent(Event event){
        eventRepo.save(event);
    }

    public List<Event> getEvents(){
        return eventRepo.findAll();
    }

    public List<Event> getAvailableTimes(LocalDate date){
        return eventRepo.getAvailableTimes(date);
    }


}
