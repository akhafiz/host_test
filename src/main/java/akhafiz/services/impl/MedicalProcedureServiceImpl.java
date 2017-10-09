package akhafiz.services.impl;

import akhafiz.dao.MedicalProcedureDao;
import akhafiz.model.MedicalProcedure;
import akhafiz.services.MedicalProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("medicalProcedureService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MedicalProcedureServiceImpl implements MedicalProcedureService {

	@Autowired
	private MedicalProcedureDao medicalProcedureDao;

	public MedicalProcedure addMedicalProcedure(MedicalProcedure medicalProcedure) {
		return medicalProcedureDao.saveOrUpdate(medicalProcedure);
	}

	public MedicalProcedure getMedicalProcedure(Long medicalProcedureId) {
		return medicalProcedureDao.findById(medicalProcedureId);
	}

	public List<MedicalProcedure> getAllMedicalProcedures() {
		return medicalProcedureDao.findAll();
	}

	public void deleteMedicalProcedure(MedicalProcedure medicalProcedure) {
		medicalProcedureDao.delete(medicalProcedure);
	}
}
