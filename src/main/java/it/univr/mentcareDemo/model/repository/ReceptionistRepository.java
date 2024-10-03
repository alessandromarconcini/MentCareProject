package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Manager;
import it.univr.mentcareDemo.model.Receptionist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends CrudRepository<Receptionist,Long> {

}
