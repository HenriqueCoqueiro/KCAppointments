package com.melo.kc_appointments.services;

import com.melo.kc_appointments.adapters.repositories.EventRepo;
import com.melo.kc_appointments.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventServices {

    @Autowired
    public EventRepo eventRepo;

    public void addEvent(Event event){
        eventRepo.save(event);
    }

    public void deleteEvent(String name){
        Event deletedEvent = eventRepo.findEventByname(name);
        eventRepo.delete(deletedEvent);
    }

    public Event getEvent(String name){
       return eventRepo.findEventByname(name);
    }

    public List<Event> getAllEvents(){
        return eventRepo.findAll();
    }

    public List<Event> getScheduledEvents(LocalDate date){
        return eventRepo.getScheduledEvents(date);
    }


    public List<LocalDateTime> getAvailableDateTime(List<LocalDateTime> scheduledDateTime, LocalDate date){
        LocalDateTime dateBase = LocalDateTime.of(date, LocalTime.of(0,0,0));

        List<LocalDateTime> dateTime = new ArrayList<>();
        Collections.sort(scheduledDateTime);

        for (int h = 0; 24*2 >= h; h++){
            dateTime.add(dateBase.plusMinutes(h*30));
        }

        int counter = 0;
        int counterEndBeg = 0;
        List<LocalDateTime> toRemove = new ArrayList<>();
        for (int i = 0; dateTime.size() > i; i++){
            for (int j = 0; j < scheduledDateTime.size(); j++) {
                if (dateTime.get(i).equals(scheduledDateTime.get(j)) && j == 1){
                    int temp = i;
                    for (int z = 0; z <= i; z ++){
                        toRemove.add(dateTime.get(temp));
                        temp--;
                    }
                }
                else if (dateTime.get(i).equals(scheduledDateTime.get(j))) {
                    toRemove.add(dateTime.get(i));
                    counterEndBeg++;
                    if (counterEndBeg == 2){
                        int temp = counter;
                        int a = i;
                        for (int z = 0; z < temp; z++){
                            toRemove.add(dateTime.get(a));
                            a--;
                            counter--;
                        }
                        counter = 0;
                        counterEndBeg = 0;
                    }
                }
            }
            if (counterEndBeg > 0) {
                counter++;
            }
        }

        dateTime.removeAll(toRemove);
        return dateTime;
    }


}
