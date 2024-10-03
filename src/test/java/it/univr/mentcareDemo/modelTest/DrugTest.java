package it.univr.mentcareDemo.modelTest;

import it.univr.mentcareDemo.model.Appointment;
import it.univr.mentcareDemo.model.Drug;
import it.univr.mentcareDemo.model.exception.IllegalAppointmentException;
import it.univr.mentcareDemo.model.exception.IllegalDrugException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DrugTest {


    // Testa un farmaco sprovvisto di campi
    @Test
    public void basicVoidDrugInitialization(){

        Drug testVoid = new Drug();

        Assertions.assertNull(testVoid.getName());
        Assertions.assertNull(testVoid.getAssignedDose());
        Assertions.assertNull(testVoid.getFrequency());
        Assertions.assertNull(testVoid.getId());
        Assertions.assertNull(testVoid.getMaximumDose());
        Assertions.assertNull(testVoid.getMinimumDose());
    }

    // Testa un farmaco standard
    @Test
    public void basicDrugInitializationTest(){

        Drug test0 = new Drug("name",13.5F,14.5F,14.0F,"Quattro al volte al giorno");

        Assertions.assertEquals("name",test0.getName());
        Assertions.assertEquals(13.5F,test0.getMinimumDose().floatValue());
        Assertions.assertEquals(14.5F,test0.getMaximumDose().floatValue());
        Assertions.assertEquals(14.0F,test0.getAssignedDose().floatValue());
        Assertions.assertEquals("Quattro al volte al giorno",test0.getFrequency());

    }

    @Test
    public void illegalDrugTest(){

        try{
            Drug test = new Drug(null,-5F,-45F,80F,null);
        } catch (IllegalDrugException e){
            assertEquals("Invalid Drug field initialization, see constructor dor details",e.getMessage());
        }
    }

    @Test
    public void illegalDrugTest2(){

        try{
            Drug test = new Drug("name",-5F,-45F,80F,"Once a week");
        } catch (IllegalDrugException e){
            assertEquals("Invalid values for minimum or maximum dose in Drug initialization, Actual assignedDose is " + 80F,e.getMessage());
        }
    }
}