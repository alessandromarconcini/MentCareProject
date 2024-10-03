package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Doctor;
import it.univr.mentcareDemo.model.Nurse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NurseRepository extends CrudRepository<Nurse,Long> {

    public default Nurse findFirstBySurname(String surname){

        for(Nurse n:findAll())
            if(n.getSurname().equals(surname))
                return n;
        return null;
    }


}
