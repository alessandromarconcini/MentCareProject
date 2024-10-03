package it.univr.mentcareDemo.model.exception;

public class IllegalDoctorException  extends IllegalArgumentException{

    public IllegalDoctorException(){super("Invalid doctor field initialization, see constructor for details");}
}
