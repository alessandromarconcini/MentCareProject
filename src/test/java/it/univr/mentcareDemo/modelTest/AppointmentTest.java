package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.Appointment;
import it.univr.mentcareDemo.model.Doctor;
import it.univr.mentcareDemo.model.Nurse;
import it.univr.mentcareDemo.model.Patient;
import it.univr.mentcareDemo.model.exception.IllegalAppointmentException;
import it.univr.mentcareDemo.model.exception.IllegalDrugException;
import it.univr.mentcareDemo.model.exception.IllegalUserException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class AppointmentTest {


    // Testa un appuntamento sprovvisto di campi
    @Test
    public void basicVoidAppointmentInitialization(){

        Appointment testVoid = new Appointment();

        Assertions.assertNull(testVoid.getDate());
        Assertions.assertNull(testVoid.getDoctor());
        Assertions.assertNull(testVoid.getNurse());
        Assertions.assertNull(testVoid.getPatient());
    }

    // Testa un appuntamento standard
    @Test
    public void basicAppointmentInitializationTest(){

        LocalDate now = LocalDate.now();
        Nurse nurse = new Nurse();
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        String hour = "10:00";

        Appointment test0 = new Appointment(nurse,now,patient,doctor, hour);

        Assertions.assertEquals(nurse,test0.getNurse());
        Assertions.assertEquals(now,test0.getDate());
        Assertions.assertEquals(doctor,test0.getDoctor());
        Assertions.assertEquals(patient,test0.getPatient());

    }

    @Test
    public void illegalAppointmentTest(){

        try{
            Appointment test = new Appointment(null,null,null,null,null);
        } catch (IllegalAppointmentException | IllegalUserException e){
            assertEquals("Invalid Appointment field is null, see constructor for details",e.getMessage());
        }
    }
}