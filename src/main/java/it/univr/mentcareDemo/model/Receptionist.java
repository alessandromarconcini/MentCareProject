package it.univr.mentcareDemo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Receptionist extends User{

    public Receptionist(String name, String surname, String password, String fiscalCode, String birthPlace, String birth) {

        super(name,surname,password,fiscalCode,birthPlace,birth);
    }

    public Receptionist(){}

    public Boolean isAReceptionist() {
        return true;
    }
}
