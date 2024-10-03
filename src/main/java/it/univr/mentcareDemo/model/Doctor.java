package it.univr.mentcareDemo.model;

import it.univr.mentcareDemo.model.exception.IllegalDoctorException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctor extends User{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointmentList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> patientList = new ArrayList<>();
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptionList = new ArrayList<>();
    private String specialization;

    public Doctor(List<Appointment> appointmentList, List<Patient> patientList,
                  String phoneNumber, List<Prescription> prescriptionList, String specialization,
                  String name, String surname, String password, String fiscalCode, String birthPlace, String birth) {

        super(name,surname,password,fiscalCode,birthPlace,birth);

        if (appointmentList == null)
            throw new IllegalDoctorException();

        if(patientList == null)
            throw new IllegalDoctorException();

        if (phoneNumber == null || phoneNumber.length() < 10 || phoneNumber.length() > 13)
            throw new IllegalDoctorException();

        if (prescriptionList == null)
            throw new IllegalDoctorException();

        if (specialization == null || specialization.length() == 0)
            throw new IllegalDoctorException();

        this.appointmentList = appointmentList;
        this.patientList = patientList;
        this.prescriptionList = prescriptionList;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;

    }

    public Doctor() { //NOTE Servono anche dei costruttori vuoti per consentire a springboot il funzionamento del database
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public boolean isDoctor() {
        return true;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public String getSpecialization() {
        return specialization;
    }
}
