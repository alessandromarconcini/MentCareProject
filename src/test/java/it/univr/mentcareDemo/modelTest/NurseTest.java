package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.Appointment;
import it.univr.mentcareDemo.model.Doctor;
import it.univr.mentcareDemo.model.Nurse;
import it.univr.mentcareDemo.model.Patient;
import it.univr.mentcareDemo.model.exception.IllegalDoctorException;
import it.univr.mentcareDemo.model.exception.IllegalNurseException;
import it.univr.mentcareDemo.model.exception.IllegalUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class NurseTest {

    // Testa un'inizializzazione infermiere
    @Test
    public void basicVoidNurseInitialization(){

        Nurse testVoid = new Nurse();

        Assertions.assertEquals(true, testVoid.isANurse());
        Assertions.assertNull(testVoid.getName());
        Assertions.assertNull(testVoid.getSurname());
        Assertions.assertNull(testVoid.getFiscalCode());
        Assertions.assertNull(testVoid.getAppointmentList());
        Assertions.assertNull(testVoid.getBirthday());
        Assertions.assertNull(testVoid.getBirthplace());
        Assertions.assertNull(testVoid.getPassword());
        Assertions.assertEquals(0, testVoid.getDailyPatientList().size());
        Assertions.assertTrue(testVoid.isANurse());

    }

    // Testa un infermiere standard
    @Test
    public void basicNurseInitializationTest(){

        List<Appointment> appointmentList = new ArrayList<>();
        List<Patient> dailyPatientList = new ArrayList<>();
        String name = "name";
        String surname = "surname";
        String fiscalCode = "CNNLSN56G98F566Y";
        String birthDate = "31/08/1998";
        String password = "pw";
        String birthplace = "UniVr";

        Nurse test0 = new Nurse(appointmentList, dailyPatientList, name, surname,
                password, fiscalCode, birthplace, birthDate);

        Assertions.assertEquals(appointmentList, test0.getAppointmentList());
        Assertions.assertEquals(dailyPatientList, test0.getDailyPatientList());
        Assertions.assertEquals(name, test0.getName());
        Assertions.assertEquals(surname, test0.getSurname());
        Assertions.assertEquals(password, test0.getPassword());
        Assertions.assertEquals(fiscalCode, test0.getFiscalCode());
        Assertions.assertEquals(birthplace, test0.getBirthplace());
        Assertions.assertEquals(birthDate, test0.getBirthday());
        Assertions.assertTrue(test0.isANurse());
    }

    @Test
    public void illegalNurseTest(){

        try{
            Nurse test = new Nurse(null,null,null,null,null,null,null,null);
        } catch (IllegalNurseException | IllegalUserException e){

            if("Invalid Nurse field is null, see constructor for details".equals(e.getMessage()) || "Invalid user for void or null field initialization, see User constructor checks for details".equals(e.getMessage()))
                assertEquals(true,true);
        }
    }

    @Test
    public void isNurseTest(){
        Nurse testIsNurse = new Nurse();
        Assertions.assertTrue(testIsNurse.isANurse());
        }
}