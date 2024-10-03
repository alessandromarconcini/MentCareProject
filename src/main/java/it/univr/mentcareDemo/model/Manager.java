package it.univr.mentcareDemo.model;

import javax.persistence.Entity;

@Entity
public class Manager extends User{

    public Manager(String name, String surname, String password, String fiscalCode, String birthPlace, String birth) {

        super(name,surname,password,fiscalCode,birthPlace,birth);
    }

    public Manager() {}

    public Boolean isManager() { return true; }
}
