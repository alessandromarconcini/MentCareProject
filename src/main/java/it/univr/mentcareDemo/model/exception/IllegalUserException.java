package it.univr.mentcareDemo.model.exception;

import java.time.LocalDate;

public class IllegalUserException extends IllegalArgumentException{

    //NOTE la keyword super si occupa di mandare la stringa in input al costruttore della superclasse, nel caso di IllegalArgumentException, il messaggio viene stampato a video da un metodo implementato in questa superclasse
    public IllegalUserException(){ super("Invalid user for void or null field initialization, see User constructor checks for details");}

}
