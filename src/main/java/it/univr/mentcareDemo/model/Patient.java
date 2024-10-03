package it.univr.mentcareDemo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends User {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointmentList = new ArrayList<>();
    private String phoneNumber;
    private Boolean dangerous = false;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prescription prescription;
    private String pathology;

    public Patient(List<Appointment> appointmentList, Prescription prescription,
                   String pathology, Boolean dangerous, String phoneNumber,
                   String name, String surname, String password, String fiscalCode, String birthPlace, String birth) {

        super(name,surname,password,fiscalCode,birthPlace,birth);

        if (appointmentList == null)
            throw new IllegalArgumentException();

        if (pathology == null || pathology.length() == 0)
            throw new IllegalArgumentException();

        if (phoneNumber == null || phoneNumber.length() > 13)
            throw new IllegalArgumentException();

        this.appointmentList = appointmentList;
        this.dangerous = dangerous;
        this.pathology = pathology;
        this.phoneNumber = phoneNumber;
        this.prescription = prescription;
    }

    public Patient() {}

    public Boolean isAPatient() { return true; }

    public Boolean isDangerous() { return dangerous; }

    public List<Appointment> getAppointmentList() {return appointmentList; }

    public void setAppointment(Appointment Appointment) {
        this.appointmentList.add(Appointment);
    }

    public String getPhoneNumber() { return phoneNumber; }

    public Prescription getPrescription() { return prescription; }

    public String getPathology() { return pathology; }

    public void setPrescription(Prescription p){ this.prescription = p;}
}