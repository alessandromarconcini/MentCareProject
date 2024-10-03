package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Appointment;
import it.univr.mentcareDemo.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Long> {

}
