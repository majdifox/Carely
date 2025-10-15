package com.carely.test;

import com.carely.entity.*;
import com.carely.enums.*;
import com.carely.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe de test pour vérifier la connexion à la base de données
 * et créer des données de test
 */
public class DatabaseConnectionTest {
    
    public static void main(String[] args) {
        System.out.println("=== Test de connexion à la base de données ===");
        
        EntityManager em = null;
        EntityTransaction transaction = null;
        
        try {
            // Obtenir l'EntityManager
            em = JPAUtil.getEntityManager();
            System.out.println("✓ EntityManager créé avec succès");
            
            // Commencer une transaction
            transaction = em.getTransaction();
            transaction.begin();
            System.out.println("✓ Transaction démarrée");
            
            // Tester la création d'entités
            System.out.println("\n=== Création de données de test ===");
            
            // 1. Créer un infirmier
            Infirmier infirmier = new Infirmier(
                "ALAMI", 
                "Fatima", 
                "f.alami@carely.ma", 
                BCrypt.hashpw("password123", BCrypt.gensalt())
            );
            infirmier.setNumeroBadge("INF001");
            infirmier.setService("Urgences");
            infirmier.setTelephone("0612345678");
            em.persist(infirmier);
            System.out.println("✓ Infirmier créé: " + infirmier.getNomComplet());
            
            // 2. Créer un médecin généraliste
            MedecinGeneraliste generaliste = new MedecinGeneraliste(
                "TAZI",
                "Mohammed",
                "m.tazi@carely.ma",
                BCrypt.hashpw("password123", BCrypt.gensalt())
            );
            generaliste.setNumeroOrdre("MG12345");
            generaliste.setTelephone("0623456789");
            em.persist(generaliste);
            System.out.println("✓ Médecin généraliste créé: " + generaliste.getNomComplet());
            
            // 3. Créer un médecin spécialiste (Cardiologue)
            MedecinSpecialiste specialiste = new MedecinSpecialiste(
                "BENNANI",
                "Leila",
                "l.bennani@carely.ma",
                BCrypt.hashpw("password123", BCrypt.gensalt()),
                Specialite.CARDIOLOGIE
            );
            specialiste.setNumeroOrdre("SP67890");
            specialiste.setTarifExpertise(300.0);
            specialiste.setTelephone("0634567890");
            em.persist(specialiste);
            System.out.println("✓ Médecin spécialiste créé: " + specialiste.getNomComplet() + 
                             " (" + specialiste.getSpecialite().getLibelle() + ")");
            
            // 4. Créer des créneaux pour le spécialiste
            LocalDate demain = LocalDate.now().plusDays(1);
            Creneau creneau1 = new Creneau(specialiste, demain, LocalTime.of(9, 0), LocalTime.of(9, 30));
            Creneau creneau2 = new Creneau(specialiste, demain, LocalTime.of(9, 30), LocalTime.of(10, 0));
            Creneau creneau3 = new Creneau(specialiste, demain, LocalTime.of(10, 0), LocalTime.of(10, 30));
            em.persist(creneau1);
            em.persist(creneau2);
            em.persist(creneau3);
            System.out.println("✓ 3 créneaux créés pour " + specialiste.getNomComplet());
            
            // 5. Créer un patient
            Patient patient = new Patient(
                "IDRISSI",
                "Ahmed",
                LocalDate.of(1985, 5, 15),
                "SS1234567890"
            );
            patient.setTelephone("0645678901");
            patient.setAdresse("123 Rue Hassan II, Casablanca");
            patient.setMutuelle("CNSS");
            patient.setAntecedents("Hypertension artérielle");
            patient.setAllergies("Pénicilline");
            patient.setInfirmier(infirmier);
            em.persist(patient);
            System.out.println("✓ Patient créé: " + patient.getNomComplet());
            
            // 6. Créer des signes vitaux pour le patient
            SignesVitaux signes = new SignesVitaux(patient);
            signes.setTensionArterielle("140/90");
            signes.setFrequenceCardiaque(75);
            signes.setTemperature(37.2);
            signes.setFrequenceRespiratoire(18);
            signes.setPoids(80.0);
            signes.setTaille(175.0);
            em.persist(signes);
            System.out.println("✓ Signes vitaux enregistrés");
            
            // Commit de la transaction
            transaction.commit();
            System.out.println("\n✓ Transaction validée avec succès!");
            
            // Vérifier que les données ont été insérées
            Long countPatients = em.createQuery("SELECT COUNT(p) FROM Patient p", Long.class).getSingleResult();
            Long countUtilisateurs = em.createQuery("SELECT COUNT(u) FROM Utilisateur u", Long.class).getSingleResult();
            Long countCreneaux = em.createQuery("SELECT COUNT(c) FROM Creneau c", Long.class).getSingleResult();
            
            System.out.println("\n=== Vérification des données ===");
            System.out.println("Nombre de patients: " + countPatients);
            System.out.println("Nombre d'utilisateurs: " + countUtilisateurs);
            System.out.println("Nombre de créneaux: " + countCreneaux);
            
            System.out.println("\n=== Test réussi! ===");
            System.out.println("✓ La base de données est correctement configurée");
            System.out.println("✓ Les entités JPA fonctionnent correctement");
            System.out.println("\n--- Identifiants de test ---");
            System.out.println("Infirmier: f.alami@carely.ma / password123");
            System.out.println("Médecin Généraliste: m.tazi@carely.ma / password123");
            System.out.println("Médecin Spécialiste: l.bennani@carely.ma / password123");
            
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.err.println("✗ Transaction annulée");
            }
            System.err.println("\n✗ ERREUR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager(em);
            JPAUtil.closeEntityManagerFactory();
            System.out.println("\nEntityManager fermé");
        }
    }
}
