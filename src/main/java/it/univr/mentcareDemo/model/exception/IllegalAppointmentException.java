package it.univr.mentcareDemo.model.exception;

public class IllegalAppointmentException extends IllegalArgumentException{

    public IllegalAppointmentException() { super("Invalid Appointment field is null, see constructor for details");}
}