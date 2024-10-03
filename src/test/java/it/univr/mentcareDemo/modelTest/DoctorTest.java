package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.*;
import it.univr.mentcareDemo.model.exception.IllegalAppointmentException;
import it.univr.mentcareDemo.model.exception.IllegalDoctorException;
import it.univr.mentcareDemo.model.exception.IllegalUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DoctorTest {


    // Testa un dottore sprovvisto di campi
    @Test
    public void basicVoidDoctorInitializationTest(){

        Doctor testVoid = new Doctor();

        Assertions.assertEquals(0,testVoid.getAppointmentList().size());
        Assertions.assertEquals(0,testVoid.getPrescriptionList().size());
        Assertions.assertEquals(0,testVoid.getPatientList().size());
        Assertions.assertNull(testVoid.getName());
        Assertions.assertNull(testVoid.getPhoneNumber());
        Assertions.assertNull(testVoid.getSpecialization());
        Assertions.assertNull(testVoid.getBirthday());
        Assertions.assertNull(testVoid.getFiscalCode());
        Assertions.assertNull(testVoid.getPassword());
        Assertions.assertNull(testVoid.getSurname());
        Assertions.assertNull(testVoid.getBirthplace());
        Assertions.assertTrue(testVoid.isDoctor());

    }

    // Testa un dottore standard
    @Test
    public void basicDoctorInitializationTest(){

        List<Appointment> appointmentList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();
        List<Prescription> prl = new ArrayList<>();
        LocalDate now = LocalDate.now();

        Doctor test0 = new Doctor(appointmentList, patientList,"3456949888",prl,"spec","name","surname","password","CNNLSN56G98F566Y","Univr","01-01-2023");

        Assertions.assertEquals(appointmentList,test0.getAppointmentList());
        Assertions.assertEquals(patientList,test0.getPatientList());
        Assertions.assertEquals("3456949888",test0.getPhoneNumber());
        Assertions.assertEquals(prl,test0.getPrescriptionList());
        Assertions.assertEquals("spec",test0.getSpecialization());
        Assertions.assertTrue(test0.isDoctor());
    }
    @Test
    public void illegalDoctorTest(){

        try{
            Doctor test = new Doctor(null,null,null,null,null,null,null,null,null,null,null);
        } catch (IllegalDoctorException | IllegalUserException e){

            if("Invalid Doctor field is null, see constructor for details".equals(e.getMessage()) || "Invalid user for void or null field initialization, see User constructor checks for details".equals(e.getMessage()))
                assertTrue(true);
        }
    }

    @Test
    public void isDoctorTest(){
        Doctor testIsDoctor = new Doctor();
        Assertions.assertTrue(testIsDoctor.isDoctor());
    }
}