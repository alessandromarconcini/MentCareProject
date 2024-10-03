package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.Drug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends CrudRepository<Drug,Long> {
}
