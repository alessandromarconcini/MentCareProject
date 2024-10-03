package it.univr.mentcareDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 A Differenza di Drug che è legato agli utenti, drug deposit invece è l'archivio dei farmaci del deposito
 */
@Entity
public class DrugDeposit {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private Float minimumDose;

    private Float maximumDose;

    public DrugDeposit(){}

    public DrugDeposit(String name,Float minimumDose, Float maximumDose){

        // Non si effattuano controlli in quanto viene utilizzato solo in ambiente di test
        this.name = name;
        this.minimumDose = minimumDose;
        this.maximumDose = maximumDose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMinimumDose() {
        return minimumDose;
    }

    public Float getMaximumDose() {
        return maximumDose;
    }
}
