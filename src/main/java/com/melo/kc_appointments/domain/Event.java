package com.melo.kc_appointments.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.melo.kc_appointments.exceptions.InvalidDateTimeException;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    @JsonIgnore
    private Double price;
    @Column
    private String description;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime beginningDate;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endingDate;



    @PrePersist
    @PreUpdate
    public  void init(){
        if (endingDate.isBefore(beginningDate)){
            throw new InvalidDateTimeException("ending cant be before the begin");
        }

        //price calc, it´s gonna change based on client´s choices
        Duration duration = Duration.between(beginningDate, endingDate);
        System.out.println(duration.toMinutes());
        int gap = (int) duration.toMinutes()/30;
        System.out.println(gap);
        price = (double) gap * 150;
        System.out.println(price);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDateTime beginningDate) {
        this.beginningDate = beginningDate;
    }

    public LocalDateTime getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDateTime endingDate) {
        this.endingDate = endingDate;
    }
}
