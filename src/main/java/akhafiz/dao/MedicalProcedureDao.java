package akhafiz.dao;

import akhafiz.model.MedicalProcedure;

import java.util.List;

public interface MedicalProcedureDao {

    MedicalProcedure findById(Long id);

    List<MedicalProcedure> findAll();

    MedicalProcedure saveOrUpdate(MedicalProcedure medicalProcedure);

    void delete(MedicalProcedure medicalProcedure);

}
