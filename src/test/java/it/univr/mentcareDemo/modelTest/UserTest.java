package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.Doctor;
import it.univr.mentcareDemo.model.User;
import it.univr.mentcareDemo.model.exception.IllegalDoctorException;
import it.univr.mentcareDemo.model.exception.IllegalUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserTest {


    // Testa un utente sprovvisto di campi
    @Test
    public void basicVoidUserInitialization(){

        User testVoid = new User();

        Assertions.assertNull(testVoid.getName());
        Assertions.assertNull(testVoid.getSurname());
        Assertions.assertNull(testVoid.getPassword());
        Assertions.assertNull(testVoid.getFiscalCode());
        Assertions.assertNull(testVoid.getBirthplace());
        Assertions.assertNull(testVoid.getBirthday());
    }

    // Testa un utente standard
    @Test
    public void basicUserInitializationTest(){

        User  test0 = new User();
        test0.setId(1L);
        test0.setName("name");
        test0.setSurname("surname");
        test0.setPassword("password");
        test0.setFiscalCode("ADFSML51T48L676N");
        test0.setBirthplace("Borgo Roma");
        test0.setBirthday("31/08/1998");
        Assertions.assertEquals("name", test0.getName());
        Assertions.assertEquals("surname", test0.getSurname());
        Assertions.assertEquals("password", test0.getPassword());
        Assertions.assertEquals("ADFSML51T48L676N", test0.getFiscalCode());
        Assertions.assertEquals("Borgo Roma", test0.getBirthplace());
        Assertions.assertEquals("31/08/1998", test0.getBirthday());
    }
}
