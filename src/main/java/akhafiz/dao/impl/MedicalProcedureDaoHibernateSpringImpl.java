package akhafiz.dao.impl;

import akhafiz.dao.MedicalProcedureDao;
import akhafiz.model.MedicalProcedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class MedicalProcedureDaoHibernateSpringImpl implements MedicalProcedureDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public MedicalProcedure findById(Long id) {
        return em.find(MedicalProcedure.class, id);
    }

    @Transactional(readOnly = true)
    public List<MedicalProcedure> findAll() {
        Query query = em.createQuery("select e from MedicalProcedure e");
        return query.getResultList();
    }

    public MedicalProcedure saveOrUpdate(MedicalProcedure medicalProcedure) {
        if (medicalProcedure.getId() == null) {
            em.persist(medicalProcedure);
        } else {
            em.merge(medicalProcedure);
        }
        em.flush();
        return medicalProcedure;
    }

    public void delete(MedicalProcedure medicalProcedure) {
        MedicalProcedure p = em.find(MedicalProcedure.class, medicalProcedure.getId());
        if (p != null) {
            em.remove(p);
        }
    }
}
