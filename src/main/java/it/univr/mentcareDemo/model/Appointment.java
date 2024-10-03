package it.univr.mentcareDemo.model;

import it.univr.mentcareDemo.model.exception.IllegalAppointmentException;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Nurse nurse;

    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional=true)
    @JoinColumn
    private Patient patient;

    private String hour;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional=true)
    @JoinColumn
    private Doctor doctor;

    public Appointment() {}

    public Appointment(Nurse nurse, LocalDate date, Patient patient, Doctor doctor, String hour) {

        if(nurse == null || date == null || patient == null || doctor == null)
            throw new IllegalAppointmentException();

        this.nurse = nurse;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.hour = hour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public LocalDate getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getHour(){
        return this.hour;
    }
}
