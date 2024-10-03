package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Patient;
import it.univr.mentcareDemo.model.Prescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription,Long> {

}