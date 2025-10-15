package com.carely.repository;

import com.carely.entity.Patient;
import com.carely.enums.StatutPatient;
import com.carely.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class PatientRepository {

    public void save(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(patient);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Patient findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Patient.class, id);
        } finally {
            em.close();
        }
    }

    public void update(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(patient);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Patient patient = em.find(Patient.class, id);
            if (patient != null) em.remove(patient);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Patient> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Patient findByNumeroSS(String numeroSS) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p WHERE p.numeroSecuriteSociale = :ss", Patient.class)
                    .setParameter("ss", numeroSS)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Patient> findByStatut(StatutPatient statut) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p WHERE p.statut = :statut", Patient.class)
                    .setParameter("statut", statut)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Patient> findByDate(LocalDate date) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p WHERE DATE(p.dateEnregistrement) = :date ORDER BY p.heureArrivee", Patient.class)
                    .setParameter("date", date)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}