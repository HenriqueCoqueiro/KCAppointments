package com.melo.kc_appointments.adapters.repositories;

import com.melo.kc_appointments.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    @Query(value = "SELECT * FROM event WHERE DATE (beginning_date) = :date",nativeQuery = true)
    public List<Event> getScheduledEvents(@Param("date") LocalDate date);


}
