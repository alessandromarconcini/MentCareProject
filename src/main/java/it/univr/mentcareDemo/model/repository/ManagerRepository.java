package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager,Long> {

    public Manager findByName(String name);
}
