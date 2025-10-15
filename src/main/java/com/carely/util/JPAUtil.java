package com.carely.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitaire pour gérer l'EntityManagerFactory et les EntityManagers
 */
public class JPAUtil {
    
    private static final String PERSISTENCE_UNIT_NAME = "carelyPU";
    private static EntityManagerFactory entityManagerFactory;
    
    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            System.err.println("Échec de la création de l'EntityManagerFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    /**
     * Obtient l'EntityManagerFactory
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    
    /**
     * Crée un nouvel EntityManager
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    
    /**
     * Ferme l'EntityManagerFactory
     */
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    
    /**
     * Ferme un EntityManager
     * @param em EntityManager à fermer
     */
    public static void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
