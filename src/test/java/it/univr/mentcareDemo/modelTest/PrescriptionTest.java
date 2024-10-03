package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.Drug;
import it.univr.mentcareDemo.model.Prescription;
import it.univr.mentcareDemo.model.exception.IllegalPrescriptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionTest {

    // Testa una prescrizione
    @Test
    public void basicVoidPrescriptionInitialization() {

        Prescription testVoid = new Prescription();

        Assertions.assertNull(testVoid.getTextReport());
        Assertions.assertNull(testVoid.getDrugList());
        Assertions.assertNull(testVoid.getLastModify());

    }

    // Testa un prescrizione standard
    @Test
    public void basicPrescriptionInitializationTest(){

        List<Drug> drugList = new ArrayList<>();
        String textReport = "abc";
        LocalDate lastModify = LocalDate.of(2022,10,10);

        Prescription test0 = new Prescription(textReport,drugList, lastModify);

        Assertions.assertEquals(drugList,test0.getDrugList());
        Assertions.assertEquals(textReport,test0.getTextReport());
        Assertions.assertEquals(lastModify,test0.getLastModify());
    }

    @Test
    public void illegalPrescriptionException(){

        try {
            Prescription test = new Prescription(null, null, null);

        } catch(IllegalPrescriptionException e){
            Assertions.assertEquals("Invalid field initialization for Prescription, see construcor for details",e.getMessage());
        }
    }
}
