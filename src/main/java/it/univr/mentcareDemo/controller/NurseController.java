package it.univr.mentcareDemo.controller;

import it.univr.mentcareDemo.model.*;
import it.univr.mentcareDemo.model.exception.IllegalPrescriptionException;
import it.univr.mentcareDemo.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NurseController {
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/getNursePrescriptionBySelectedRow")
    public String getNursePrescriptionBySelectedRow(@RequestParam(name = "selectedRow", required = true) String selectedRow,
                                                     @RequestParam(name = "id")Long id,
                                                     Model model){

        if(selectedRow.equals("")) {
            model.addAttribute("id", id);
            return "homeNurse";
        }
        try {
            Long patientId = Long.parseLong(selectedRow);

            Optional<Nurse> on = nurseRepository.findById(id);
            Optional<Patient> op = patientRepository.findById(patientId);

            if (on.isPresent() && op.isPresent()) {
                Nurse n = on.get();
                Patient p = op.get();

                model.addAttribute("prescription", p.getPrescription());
                return "getPatientPrescription";
            }
        }catch (IllegalPrescriptionException e){
            return "illegalPrescriptionException";
        }
        return "notfound";
    }
}
