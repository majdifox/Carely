package com.carely.repository;

import com.carely.entity.Consultation;
import com.carely.enums.StatutConsultation;
import com.carely.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ConsultationRepository {

    public void save(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(consultation);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Consultation findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Consultation.class, id);
        } finally {
            em.close();
        }
    }

    public void update(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(consultation);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Consultation> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Consultation c", Consultation.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Consultation> findByStatut(StatutConsultation statut) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Consultation c WHERE c.statut = :statut", Consultation.class)
                    .setParameter("statut", statut)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Consultation> findByPatient(Long patientId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Consultation c WHERE c.patient.id = :pid", Consultation.class)
                    .setParameter("pid", patientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}