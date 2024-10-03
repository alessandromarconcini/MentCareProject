package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.Manager;
import it.univr.mentcareDemo.model.Receptionist;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ReceptionistTest {


    // Testa un receptionist sprovvisto di campi
    @Test
    public void basicVoidDoctorInitializationTest(){

        Receptionist testVoid = new Receptionist();

        Assertions.assertNull(testVoid.getName());
        Assertions.assertNull(testVoid.getBirthday());
        Assertions.assertNull(testVoid.getFiscalCode());
        Assertions.assertNull(testVoid.getPassword());
        Assertions.assertNull(testVoid.getSurname());
        Assertions.assertNull(testVoid.getBirthplace());
        Assertions.assertTrue(testVoid.isAReceptionist());
    }

    // Testa un receptionist standard
    @Test
    public void basicReceptionistInitializationTest(){

        String birthDate = "31/08/1998";

        Receptionist test0 = new Receptionist("name","surname","password","CNNLSN56G98F566Y","Univr",birthDate);

        Assertions.assertEquals("name",test0.getName());
        Assertions.assertEquals("surname",test0.getSurname());
        Assertions.assertEquals("password",test0.getPassword());
        Assertions.assertEquals("CNNLSN56G98F566Y",test0.getFiscalCode());
        Assertions.assertEquals("Univr",test0.getBirthplace());
        Assertions.assertEquals("31/08/1998",test0.getBirthday());
        Assertions.assertTrue(test0.isAReceptionist());
    }

    @Test
    public void illegalReceptionistTest(){

        try{
            Receptionist test = new Receptionist(null,null,null,null,null,null);
        } catch (IllegalArgumentException e){
            assertEquals(true,true);
        }
    }

    @Test
    public void isReceptionistTest(){
        Receptionist testIsReceptionist = new Receptionist();
        Assertions.assertTrue(testIsReceptionist.isAReceptionist());
    }
}
