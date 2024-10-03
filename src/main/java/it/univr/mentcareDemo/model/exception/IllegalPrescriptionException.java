package it.univr.mentcareDemo.model.exception;

public class IllegalPrescriptionException extends IllegalArgumentException{

    public IllegalPrescriptionException(){ super("Invalid field initialization for Prescription, see construcor for details");}
}
