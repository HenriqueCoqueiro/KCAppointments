package com.melo.kc_appointments.services;

import com.melo.kc_appointments.adapters.repositories.EventRepo;
import com.melo.kc_appointments.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public List<Event> getScheduledEvents(LocalDate date){
        return eventRepo.getScheduledEvents(date);
    }


    public List<LocalDateTime> getAvailableDateTime(List<LocalDateTime> scheduledDateTime, LocalDate date){
        LocalDateTime dateBase = LocalDateTime.of(date, LocalTime.of(0,0,0));

        List<LocalDateTime> dateTime = new ArrayList<>();

        for (int h = 0; 24*2 > h; h++){
            dateTime.add(dateBase.plusMinutes(h*30));
        }

        for (int i = 0; dateTime.size() > i; i++){
            for (LocalDateTime scheduledDate : scheduledDateTime) {
                if (dateTime.get(i).equals(scheduledDate)) {
                    dateTime.remove(i);
                }
            }
        }
        return dateTime;
    }


}
