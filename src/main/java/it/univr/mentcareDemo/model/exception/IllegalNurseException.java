package it.univr.mentcareDemo.model.exception;

public class IllegalNurseException extends IllegalArgumentException{

    public IllegalNurseException() { super("Invalid Nurse fields, see constructor for details");}
}
