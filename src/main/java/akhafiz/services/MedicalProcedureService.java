package akhafiz.services;

import akhafiz.model.MedicalProcedure;

import java.util.List;

public interface MedicalProcedureService {

	MedicalProcedure addMedicalProcedure(MedicalProcedure medicalProcedure);

	MedicalProcedure getMedicalProcedure(Long medicalProcedureId);

	List<MedicalProcedure> getAllMedicalProcedures();

	void deleteMedicalProcedure(MedicalProcedure medicalProcedure);
}
