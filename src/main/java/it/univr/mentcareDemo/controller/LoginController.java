package it.univr.mentcareDemo.controller;

import it.univr.mentcareDemo.model.*;
import it.univr.mentcareDemo.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping("/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        Model model) {

        try{
            long id = Long.parseLong(username);

            for (User user : userRepository.findAll()) {

                if (user.getId() == id && user.getPassword().equals(password)) {

                    if (user instanceof Patient && ((Patient) user).isAPatient()) {

                        model.addAttribute("id",id);
                        return "homePatient";
                    }
                    if (user instanceof Doctor && ((Doctor) user).isDoctor()){

                        model.addAttribute("id",id);
                        return "homeDoctor";
                    }
                    if (user instanceof Manager && ((Manager) user).isManager()) {

                        model.addAttribute("id",id);
                        return "homeManager";
                    }
                    if (user instanceof Receptionist && ((Receptionist) user).isAReceptionist()) {

                        model.addAttribute("id",id);
                        return "homeReceptionist";
                    }
                    if (user instanceof Nurse && ((Nurse) user).isANurse()) {

                        model.addAttribute("id",id);
                        return "homeNurse";
                    }
                }
            }
        } catch (RuntimeException e) {
            return "notfound";
        }
        return "notfound";
    }

    @RequestMapping("/logout")
    public String logout(){ return "redirect:";}

    public List<Appointment> getAppointmentsForPatient(Long id){

        List<Appointment> appointmentList = new ArrayList<>();
        for(Appointment a: appointmentRepository.findAll())
            if(a.getPatient().getId().equals(id))
                appointmentList.add(a);

        return appointmentList;
    }

    public List<Appointment> getAppointmentsForDoctor(Long id){

        List<Appointment> appointmentList = new ArrayList<>();
        for(Appointment a: appointmentRepository.findAll())
            if(a.getDoctor().getId().equals(id))
                appointmentList.add(a);

        return appointmentList;
    }

    public List<Appointment> getAppointmentsForNurse(Long id){

        List<Appointment> appointmentList = new ArrayList<>();
        for(Appointment a: appointmentRepository.findAll())
            if(a.getNurse().getId().equals(id))
                appointmentList.add(a);

        return appointmentList;

    }

    @RequestMapping("/homeNurse")
    public String homeNurse(@RequestParam(name = "id", required = true)Long id, Model model){

        model.addAttribute("appointmentList", getAppointmentsForNurse(id));
        model.addAttribute("id",id);
        return "profilePageNurse";
    }

    @RequestMapping("/homeDoctor")
    public String homeDoctor(@RequestParam(name = "id", required = true)Long id, Model model){

        model.addAttribute("appointmentList", getAppointmentsForDoctor(id));
        model.addAttribute("id",id);
        return "profilePageDoctor";
    }

    @RequestMapping("/homeManager")
    public String homeManager(@RequestParam(name = "id", required = true)Long id, Model model){

        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("managerId",id);
        return "profilePageManager";
    }

    @RequestMapping("/homePatient")
    public String homePatient(@RequestParam(name = "id", required = true)Long id, Model model){

        model.addAttribute("appointmentList", getAppointmentsForPatient(id));
        return "profilePagePatient";
    }

    @RequestMapping("/homeReceptionist")
    public String homeReceptionist(@RequestParam(name = "id", required = true)Long id, Model model){

        model.addAttribute("appointmentList", appointmentRepository.findAll());
        return "profilePageReceptionist";
    }
}
