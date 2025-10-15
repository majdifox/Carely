package com.carely.repository;

import com.carely.entity.SignesVitaux;
import com.carely.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class SignesVitauxRepository {

    public void save(SignesVitaux signes) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(signes);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public SignesVitaux findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(SignesVitaux.class, id);
        } finally {
            em.close();
        }
    }

    public void update(SignesVitaux signes) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(signes);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<SignesVitaux> findByPatient(Long patientId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT s FROM SignesVitaux s WHERE s.patient.id = :pid ORDER BY s.dateMesure DESC", SignesVitaux.class)
                    .setParameter("pid", patientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}