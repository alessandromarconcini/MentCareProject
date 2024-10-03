package it.univr.mentcareDemo.controller;

import it.univr.mentcareDemo.model.*;
import it.univr.mentcareDemo.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReceptionistController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NurseRepository nurseRepository;

    @RequestMapping("/createAppointmentFromPage")
    public String createAppointmentFromPage(@RequestParam(name="doctorSurname", required=true) String doctorSurname,
                                  @RequestParam(name= "patientName",required = true) String patientName,
                                  @RequestParam(name= "patientSurname",required = true) String patientSurname,
                                  @RequestParam(name= "nurseSurname", required=true) String nurseSurname,
                                  @RequestParam(name = "date",required = true) String date,
                                  @RequestParam(name= "hour", required=true) String hour,
                                  @RequestParam("patientId")Long patientId,
                                  @RequestParam("receptionistId")Long receptionistId,
                                  Model model){

        return manageAppointment(doctorSurname, nurseSurname, date, hour, patientId, receptionistId, model);
    }

    @RequestMapping("/updateAppointmentFromPage")
    public String updateAppointmentFromPage(@RequestParam(name="doctorSurname", required=true) String doctorSurname,
                                            @RequestParam(name= "patientName",required = true) String patientName,
                                            @RequestParam(name= "patientSurname",required = true) String patientSurname,
                                            @RequestParam(name= "nurseSurname", required=true) String nurseSurname,
                                            @RequestParam(name = "date",required = true) String date,
                                            @RequestParam(name= "hour", required=true) String hour,
                                            @RequestParam("patientId")Long patientId,
                                            @RequestParam("receptionistId")Long receptionistId,
                                            @RequestParam("prevAppointment")Long prevAppointment,
                                            Model model){

        Appointment a = appointmentRepository.findById(prevAppointment).get();

        a.setPatient(null);
        a.setDoctor(null);
        a.setNurse(null);
        appointmentRepository.delete(a);

        return manageAppointment(doctorSurname, nurseSurname, date, hour, patientId, receptionistId, model);
    }

    private String manageAppointment(@RequestParam(name = "doctorSurname", required = true) String doctorSurname, @RequestParam(name = "nurseSurname", required = true) String nurseSurname, @RequestParam(name = "date", required = true) String date, @RequestParam(name = "hour", required = true) String hour, @RequestParam("patientId") Long patientId, @RequestParam("receptionistId") Long receptionistId, Model model) {

        LocalDate localDate = LocalDate.parse(date);

        Doctor d = doctorRepository.findFirstBySurname(doctorSurname);
        Nurse n = nurseRepository.findFirstBySurname(nurseSurname);
        Optional<Patient> op = patientRepository.findById(patientId);

        if (d.isDoctor() && n.isANurse() && op.isPresent() && op.get().isAPatient()) {

            Appointment appointment = new Appointment(n, localDate, op.get(),d, hour);
            appointmentRepository.save(appointment);

            model.addAttribute("id",receptionistId);
            return "homeReceptionist";
        }
        return "notfound";
    }

    @RequestMapping("/deleteAppointment")
    public String deleteAppointment (@RequestParam(name = "appointmentId", required = true) Long appointmentId,
                                     Model model){

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);

        if (appointment.isPresent()){

            appointment.get().setDoctor(null);
            appointment.get().setPatient(null);
            appointment.get().setNurse(null);

            appointmentRepository.save(appointment.get());
            appointmentRepository.delete(appointment.get());

            model.addAttribute("appointmentList", appointmentRepository.findAll());
            return "profilePageReceptionist";
        }else
            return "notfound";
    }

    @RequestMapping("/updateAppointment")
    public String updateAppointment (@RequestParam(name = "appointment", required = true) Long appointmentId,
                                     Model model){

        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        Appointment app;

        if(appointment.isPresent()) {

            app = appointment.get();
            model.addAttribute("appointment",app);
            model.addAttribute("doctorList",doctorRepository.findAll());
            model.addAttribute("nurseList",nurseRepository.findAll());
            return "updateAppointment";
        }

        return "notfound";
    }

    @RequestMapping("/createAppointment")
    public String createAppointment(Model model){

        model.addAttribute("doctorList",doctorRepository.findAll());
        model.addAttribute("nurseList",nurseRepository.findAll());
        return "createAppointment";}

}
