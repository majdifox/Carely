package com.carely.entity;

import com.carely.enums.Role;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un médecin généraliste
 * Responsable des consultations et des demandes d'expertise
 */
@Entity
@Table(name = "medecins_generalistes")
public class MedecinGeneraliste extends Utilisateur {
    
    @Column(name = "numero_ordre", unique = true, length = 20)
    private String numeroOrdre; // Numéro d'inscription à l'ordre des médecins
    
    @Column(name = "tarif_consultation", nullable = false)
    private double tarifConsultation = 150.0; // Tarif fixe : 150 DH
    
    @OneToMany(mappedBy = "medecinGeneraliste", cascade = CascadeType.ALL)
    private List<Consultation> consultations = new ArrayList<>();
    
    @OneToMany(mappedBy = "medecinDemandeur", cascade = CascadeType.ALL)
    private List<DemandeExpertise> demandesExpertise = new ArrayList<>();
    
    // Constructeurs
    public MedecinGeneraliste() {
        super();
        setRole(Role.MEDECIN_GENERALISTE);
    }
    
    public MedecinGeneraliste(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, Role.MEDECIN_GENERALISTE);
    }
    
    // Getters et Setters
    public String getNumeroOrdre() {
        return numeroOrdre;
    }
    
    public void setNumeroOrdre(String numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }
    
    public double getTarifConsultation() {
        return tarifConsultation;
    }
    
    public void setTarifConsultation(double tarifConsultation) {
        this.tarifConsultation = tarifConsultation;
    }
    
    public List<Consultation> getConsultations() {
        return consultations;
    }
    
    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
    
    public List<DemandeExpertise> getDemandesExpertise() {
        return demandesExpertise;
    }
    
    public void setDemandesExpertise(List<DemandeExpertise> demandesExpertise) {
        this.demandesExpertise = demandesExpertise;
    }
}
