package it.univr.mentcareDemo.model;

import it.univr.mentcareDemo.model.exception.IllegalNurseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Nurse  extends User{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointmentList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> dailyPatientList = new ArrayList<>();

    public Nurse(List<Appointment> appointmentList, List<Patient> dailyPatientList,
                 String name, String surname, String password, String fiscalCode, String birthPlace, String birth) {

        super(name,surname,password,fiscalCode,birthPlace,birth);

        if (appointmentList == null)
            throw new IllegalNurseException();
        if (dailyPatientList == null)
            throw new IllegalNurseException();

        this.appointmentList = appointmentList;
        this.dailyPatientList = dailyPatientList;
    }
    public Nurse() {}

    public Boolean isANurse() {return true; }


    public List<Appointment> getAppointmentList() { return appointmentList; }

    public List<Patient> getDailyPatientList() { return dailyPatientList; }

}
