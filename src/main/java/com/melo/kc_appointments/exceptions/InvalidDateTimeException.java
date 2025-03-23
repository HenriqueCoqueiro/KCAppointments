package com.melo.kc_appointments.exceptions;

public class InvalidDateTimeException extends RuntimeException{


    public InvalidDateTimeException(String message) {
        super(message);
    }

    public InvalidDateTimeException(String message, Throwable cause) {
        super(message, cause);
    }


}
