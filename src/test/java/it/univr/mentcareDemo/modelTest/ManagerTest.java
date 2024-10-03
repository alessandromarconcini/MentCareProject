package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.Doctor;
import it.univr.mentcareDemo.model.Manager;
import it.univr.mentcareDemo.model.Nurse;
import it.univr.mentcareDemo.model.exception.IllegalDoctorException;
import it.univr.mentcareDemo.model.exception.IllegalUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class ManagerTest {


    // Testa un membro dello staff di amministrazione sprovvisto di campi
    @Test
    public void basicVoidManagerInitialization(){

        Manager testVoid = new Manager();

        Assertions.assertNull(testVoid.getName());
        Assertions.assertNull(testVoid.getBirthday());
        Assertions.assertNull(testVoid.getBirthplace());
        Assertions.assertNull(testVoid.getFiscalCode());
        Assertions.assertNull(testVoid.getPassword());
        Assertions.assertNull(testVoid.getSurname());
        Assertions.assertTrue(testVoid.isManager());
    }

    // Testa un membro dello staff di amministrazione standard
    @Test
    public void basicManagerInitializationTest(){


        Manager test0 = new Manager("name","surname","password","CNNLSN56G98F566Y","Univr", "01-01-2023");

        Assertions.assertEquals("name",test0.getName());
        Assertions.assertEquals("surname",test0.getSurname());
        Assertions.assertEquals("password",test0.getPassword());
        Assertions.assertEquals("CNNLSN56G98F566Y",test0.getFiscalCode());
        Assertions.assertEquals("Univr",test0.getBirthplace());
        Assertions.assertEquals("01-01-2023",test0.getBirthday());
        Assertions.assertTrue(test0.isManager());
    }

    @Test
    public void illegalManagerTest(){

        try{
            Manager test = new Manager(null,null,null,null,null,null);
        } catch (IllegalUserException e){
            assertEquals("Invalid user for void or null field initialization, see User constructor checks for details",e.getMessage());
        }
    }

    @Test
    public void isManagerTest(){
        Manager testIsManager = new Manager();
        Assertions.assertTrue(testIsManager.isManager());
    }
}