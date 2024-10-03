package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long> {

    public default Doctor findFirstBySurname(String surname){

        for(Doctor d:findAll())
            if(d.getSurname().equals(surname))
                return d;
        return null;
    }

}