package akhafiz.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class MedicalProcedure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal price;

    private String medicalOfficerFio;

    private String cabinetNumber;

    public MedicalProcedure() {
    }

    public MedicalProcedure(String name, BigDecimal price, String medicalOfficerFio, String cabinetNumber) {
        this.name = name;
        this.price = price;
        this.medicalOfficerFio = medicalOfficerFio;
        this.cabinetNumber = cabinetNumber;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMedicalOfficerFio() {
        return medicalOfficerFio;
    }

    public void setMedicalOfficerFio(String medicalOfficerFio) {
        this.medicalOfficerFio = medicalOfficerFio;
    }

    public String getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(String cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        MedicalProcedure other = (MedicalProcedure) obj;

        return other.id != null && other.id.equals(this.id);
    }

}
