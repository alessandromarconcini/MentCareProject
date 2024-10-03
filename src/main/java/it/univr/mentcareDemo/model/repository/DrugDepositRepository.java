package it.univr.mentcareDemo.model.repository;

import it.univr.mentcareDemo.model.DrugDeposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugDepositRepository extends CrudRepository<DrugDeposit,Long> {

    public DrugDeposit findByName(String name);
}
