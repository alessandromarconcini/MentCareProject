package it.univr.mentcareDemo.model;

import it.univr.mentcareDemo.model.exception.IllegalUserException;

import javax.persistence.*;


/**
 * Classe rappresentante un utente generico del sistema Mentcare
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String fiscalCode;
    private String birthplace;
    private String birthday;
    public User(String name, String surname, String password, String fiscalCode, String birthplace,
                String birthday) {

        if(name == null || name.length() == 0)
            throw new IllegalUserException();
        if(surname == null || surname.length() == 0)
            throw new IllegalUserException();
        if(password == null || password.length() == 0)
            throw new IllegalUserException();
        if(fiscalCode == null || fiscalCode.length() != 16 )
            throw new IllegalUserException();
        if(birthplace == null || birthplace.length() == 0)
            throw new IllegalUserException();

        this.name = name;
        this.surname = surname;
        this.password = password;
        this.fiscalCode = fiscalCode;
        this.birthplace = birthplace;
        this.birthday = birthday;
    }

    public User() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getType(){

        if(this instanceof Patient)
            return "Patient";
        if(this instanceof Manager)
            return "Manager";
        if(this instanceof Receptionist)
            return "Receptionist";
        if(this instanceof Doctor)
            return "Doctor";
        if(this instanceof Nurse)
            return "Nurse";

        return "ERROR";
    }

}
