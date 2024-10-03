package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Long> {
    public Optional<Patient> findBySurname(String surname);
}
