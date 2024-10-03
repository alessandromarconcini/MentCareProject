package it.univr.mentcareDemo.controller;


import it.univr.mentcareDemo.model.*;
import it.univr.mentcareDemo.model.repository.*;
import org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ManagerController{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ReceptionistRepository receptionistRepository;

    @RequestMapping("/createNurseFromPage")
    public String createNurseFromPage(@RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("password") String password,
                             @RequestParam("fiscalCode") String fiscalCode,
                             @RequestParam("birthplace") String birthplace,
                             @RequestParam("birth") String birthDate,
                             @RequestParam("id")Long id,
                             Model model){

            Nurse n = new Nurse(new ArrayList<>(), new ArrayList<>(), name, surname, password, fiscalCode, birthplace, birthDate);
            nurseRepository.save(n);

            model.addAttribute("id",id);
            return "homeManager";
        }

    @RequestMapping("/createDoctorFromPage")
    public String createDoctorFromPage(@RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("password") String password,
                               @RequestParam("fiscalCode") String fiscalCode,
                               @RequestParam("birthplace") String birthplace,
                               @RequestParam("specialization")String specialization,
                               @RequestParam("phoneNumber")String phoneNumber,
                               @RequestParam("birth")String birthDate,
                               @RequestParam(value = "id",required = true) Long id,
                                       Model model){

        Doctor d = new Doctor(new ArrayList<>(), new ArrayList<>(), phoneNumber,new ArrayList<>(), specialization, name, surname, password, fiscalCode, birthplace, birthDate);
        doctorRepository.save(d);

        model.addAttribute("id",id);
        return "homeManager";
    }


    @RequestMapping("/createPatientFromPage")
    public String createPatientFromPage(@RequestParam("name") String name,
                              @RequestParam("surname") String surname,
                              @RequestParam("password") String password,
                              @RequestParam("fiscalCode") String fiscalCode,
                              @RequestParam("birthplace") String birthplace,
                              @RequestParam("phoneNumber")String phoneNumber,
                              @RequestParam("dangerous")String dangerous,
                              @RequestParam("pathology")String pathology,
                              @RequestParam("birth")String birthDate,
                              @RequestParam("id")Long id,
                              Model model){

        Boolean danger = false;
        if(dangerous.contains("S"))
            danger = true;

        Patient p  = new Patient(new ArrayList<>(),new Prescription(), pathology, danger, phoneNumber, name, surname, password, fiscalCode, birthplace, birthDate);
        patientRepository.save(p);

        model.addAttribute("id",id);
        return "homeManager";
        }

    @RequestMapping("/createReceptionistFromPage")
    public String createReceptionistFromPage(@RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
                                   @RequestParam("password") String password,
                                   @RequestParam("fiscalCode") String fiscalCode,
                                   @RequestParam("birthplace") String birthplace,
                                   @RequestParam("birth")String birthDate,
                                   @RequestParam("id")Long id,
                                   Model model){

        Receptionist r = new Receptionist(name,surname,password,fiscalCode,birthplace,birthDate);
        receptionistRepository.save(r);

        model.addAttribute("id",id);
        return "homeManager";
    }

    @RequestMapping("createDoctor")
    public String createDoctorPage(@RequestParam(value = "id",required = true) Long id,Model model){

        model.addAttribute("id",id);
        return "createDoctor";
    }

    @RequestMapping("createPatient")
    public String createPatienPage(){return "createPatient";}
    @RequestMapping("createNurse")
    public String createNursePage(){return "createNurse";}

    @RequestMapping("createReceptionist")
    public String createReceptionistPage(){return "createReceptionist";}

    @RequestMapping("/searchUser")
    public String searchUser(@RequestParam(name = "research",required = true)String surname, Model model){

        List<User> userList = new ArrayList<>();

        for(User u:userRepository.findAll())
            if(u.getSurname().contains(surname))
                userList.add(u);

        model.addAttribute("userList",userList);
        return "profilePageManager";
    }
}
