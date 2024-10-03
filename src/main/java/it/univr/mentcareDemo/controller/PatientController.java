package it.univr.mentcareDemo.controller;

import it.univr.mentcareDemo.model.*;
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
public class PatientController  {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @RequestMapping("profilePagePatient")
    public String getPatientAllAppointments(@RequestParam(name="id", required=true) Long id,
                                                       Model model){

        List<Appointment> appointmentList = new ArrayList<>();

        if(patientRepository.findById(id).isPresent()) {

            Patient p = patientRepository.findById(id).get();

            for (Appointment a : appointmentRepository.findAll())
                if (a.getPatient().getId().equals(p.getId()))
                    appointmentList.add(a);

        }
        model.addAttribute("appointmentList", appointmentList);
        return "profilePagePatient";
    }

    @RequestMapping("/getPatientPrescription")
    public String getPatientPrescription(@RequestParam(name="id", required=true) Long id,
                                     Model model){

        if(prescriptionRepository.findById(id).isPresent()) {
            model.addAttribute("prescription", prescriptionRepository.findById(id).get()); //passo l'intera prescrizione
            return "getPatientPrescription";
        }
        return "illegalPrescriptionException";
    }



    @RequestMapping("/searchPatient")
    public String searchPatient(@RequestParam(name = "research",required = true)String surname,
                                @RequestParam(name = "resid",required = true)Long id,
                                Model model){

        List<Appointment> appointmentList = new ArrayList<>();

        for(Appointment a: appointmentRepository.findAll()){
            if(a.getPatient().getSurname().toLowerCase().contains(surname.toLowerCase()))
                appointmentList.add(a);
        }

        model.addAttribute("appointmentList",appointmentList);
        model.addAttribute("id",id);
        return "profilePageDoctor";
    }

    @RequestMapping("/searchDailyPatients")
    public String searchDailyPatients(@RequestParam(name = "research",required = true)String surname,Model model){

        List<Appointment> appointmentList = new ArrayList<>();

        for(Appointment a: appointmentRepository.findAll()){
            if(a.getPatient().getSurname().toLowerCase().contains(surname.toLowerCase()) && a.getDate().equals(LocalDate.now()))
                appointmentList.add(a);
        }

        model.addAttribute("appointmentList",appointmentList);
        return "profilePageNurse";
    }

    @RequestMapping("/searchPatientFromReceptionist")
    public String searchPatientFromReceptionist(@RequestParam(name = "research",required = true)String surname,Model model){

        List<Appointment> appointmentList = new ArrayList<>();

        for(Appointment a: appointmentRepository.findAll()){
            if(a.getPatient().getSurname().toLowerCase().contains(surname.toLowerCase()))
                appointmentList.add(a);
        }

        model.addAttribute("appointmentList",appointmentList);
        return "profilePageReceptionist";
    }
}
