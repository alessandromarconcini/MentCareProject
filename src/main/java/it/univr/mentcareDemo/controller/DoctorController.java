package it.univr.mentcareDemo.controller;

import it.univr.mentcareDemo.model.*;
import it.univr.mentcareDemo.model.exception.IllegalDoctorException;
import it.univr.mentcareDemo.model.exception.IllegalDrugException;
import it.univr.mentcareDemo.model.repository.DoctorRepository;
import it.univr.mentcareDemo.model.repository.DrugDepositRepository;
import it.univr.mentcareDemo.model.repository.PatientRepository;
import it.univr.mentcareDemo.model.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DoctorController{

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DrugDepositRepository drugDepositRepository;

    @RequestMapping("/createPrescriptionFromPage")
    public String createPrescription(@RequestParam(name = "doctorId", required = true) Long doctorId,
                                           @RequestParam(name = "patientId", required = true) Long patientId,
                                           @RequestParam(name = "drug1", required = true) String name1,
                                           @RequestParam(name = "drug2", required = true) String name2,
                                           @RequestParam(name = "drug3", required = true) String name3,
                                           @RequestParam(name = "dose1",required = true) Float dose1,
                                           @RequestParam(name = "dose2",required = true) Float dose2,
                                           @RequestParam(name = "dose3",required = true) Float dose3,
                                           @RequestParam(name = "frequency1",required = true) String frequency1,
                                           @RequestParam(name = "frequency2",required = true) String frequency2,
                                           @RequestParam(name = "frequency3",required = true) String frequency3,
                                           @RequestParam(name = "textReport", required = true) String textReport,
                                           Model model) {

        try {
            LocalDate lastModify = LocalDate.now();

            List<DrugDeposit> drugDepositList = new ArrayList<>();

            for (DrugDeposit dd : drugDepositRepository.findAll())
                drugDepositList.add(dd);

            List<Drug> drugList = new ArrayList<>();
            Drug drug1 = null, drug2 = null, drug3 = null;

            for (DrugDeposit dd : drugDepositList) {

                if (name1.equals(dd.getName()))
                    drug1 = new Drug(name1, dd.getMinimumDose(), dd.getMaximumDose(), dose1, frequency1);
                if (name2.equals(dd.getName()))
                    drug2 = new Drug(name2, dd.getMinimumDose(), dd.getMaximumDose(), dose2, frequency2);
                if (name3.equals(dd.getName()))
                    drug3 = new Drug(name3, dd.getMinimumDose(), dd.getMaximumDose(), dose3, frequency3);
            }

            drugList.add(drug1);
            drugList.add(drug2);
            drugList.add(drug3);

            Prescription prescription = new Prescription(textReport, drugList, lastModify);
            prescriptionRepository.save(prescription);

            Optional<Patient> op = patientRepository.findById(patientId);
            if (op.isPresent()) {
                Patient patient = op.get();
                patient.setPrescription(prescription);
                patientRepository.save(patient);

                model.addAttribute("id", doctorId);
                return "homeDoctor";
            }
            return "notfound";
        } catch (IllegalDrugException e) {
            return "illegalDrugException";
        }
    }

    @RequestMapping("/getDoctorPrescriptionBySelectedRow")
    public String getDoctorPrescriptionBySelectedRow(@RequestParam(name = "selectedRow", required = true) String selectedRow,
                                           @RequestParam(name = "id")Long id,
                                           Model model){

       if(selectedRow.equals("")) {
           model.addAttribute("id", id);
           return "homeDoctor";
       }

        try {
            Long patientId = Long.parseLong(selectedRow);

            Optional<Doctor> od = doctorRepository.findById(id);
            Optional<Patient> op = patientRepository.findById(patientId);

            if (od.isPresent() && op.isPresent()) {
                Doctor d = od.get();
                Patient p = op.get();

                model.addAttribute("prescription", p.getPrescription());
                return "getPatientPrescription";
            }
        }catch (IllegalDoctorException e){
            return "illegalDoctorException";
        }
        return "notfound";
    }

    @RequestMapping("createPrescription")
    public String createPrescriptionPage(@RequestParam(name = "doctorId")Long id,Model model){

        List<DrugDeposit> drugDepositList = new ArrayList<>();

        for(DrugDeposit dd:drugDepositRepository.findAll()){
            drugDepositList.add(dd);
        }

        model.addAttribute("id",id);
        model.addAttribute("drugDepositList",drugDepositList);
        return "createPrescription";}
}
