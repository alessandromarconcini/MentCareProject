package it.univr.mentcareDemo.model;

import it.univr.mentcareDemo.model.exception.IllegalDrugException;

import javax.persistence.*;

@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Float minimumDose;
    private Float maximumDose;
    private Float assignedDose;
    private String frequency;

    public Drug(){}

    public Drug(String name, Float minimumDose, Float maximumDose, Float assignedDose, String frequency) {

        if(name == null || minimumDose == null || maximumDose == null || assignedDose == null || frequency == null)
            throw new IllegalDrugException();
        if(minimumDose < 0 || maximumDose < 0 || assignedDose < 0 || maximumDose < minimumDose || assignedDose > maximumDose || assignedDose < minimumDose)
            throw  new IllegalDrugException(assignedDose);

        this.name = name;
        this.minimumDose = minimumDose;
        this.maximumDose = maximumDose;
        this.assignedDose = assignedDose;
        this.frequency = frequency;
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

    public Float getAssignedDose() {
        return assignedDose;
    }
    public String getFrequency() {
        return frequency;
    }
}
