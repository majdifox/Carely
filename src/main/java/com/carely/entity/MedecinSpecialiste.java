package com.carely.entity;

import com.carely.enums.Role;
import com.carely.enums.Specialite;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un médecin spécialiste
 * Fournit des avis d'expert et gère ses créneaux de disponibilité
 */
@Entity
@Table(name = "medecins_specialistes")
public class MedecinSpecialiste extends Utilisateur {
    
    @Column(name = "numero_ordre", unique = true, length = 20)
    private String numeroOrdre;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Specialite specialite;
    
    @Column(name = "tarif_expertise", nullable = false)
    private double tarifExpertise = 200.0; // Tarif configurable
    
    @Column(name = "duree_consultation", nullable = false)
    private int dureeConsultation = 30; // En minutes (fixe : 30 min)
    
    @OneToMany(mappedBy = "medecinSpecialiste", cascade = CascadeType.ALL)
    private List<DemandeExpertise> expertises = new ArrayList<>();
    
    @OneToMany(mappedBy = "medecinSpecialiste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Creneau> creneaux = new ArrayList<>();
    
    // Constructeurs
    public MedecinSpecialiste() {
        super();
        setRole(Role.MEDECIN_SPECIALISTE);
    }
    
    public MedecinSpecialiste(String nom, String prenom, String email, String motDePasse, Specialite specialite) {
        super(nom, prenom, email, motDePasse, Role.MEDECIN_SPECIALISTE);
        this.specialite = specialite;
    }
    
    // Getters et Setters
    public String getNumeroOrdre() {
        return numeroOrdre;
    }
    
    public void setNumeroOrdre(String numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }
    
    public Specialite getSpecialite() {
        return specialite;
    }
    
    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    
    public double getTarifExpertise() {
        return tarifExpertise;
    }
    
    public void setTarifExpertise(double tarifExpertise) {
        this.tarifExpertise = tarifExpertise;
    }
    
    public int getDureeConsultation() {
        return dureeConsultation;
    }
    
    public void setDureeConsultation(int dureeConsultation) {
        this.dureeConsultation = dureeConsultation;
    }
    
    public List<DemandeExpertise> getExpertises() {
        return expertises;
    }
    
    public void setExpertises(List<DemandeExpertise> expertises) {
        this.expertises = expertises;
    }
    
    public List<Creneau> getCreneaux() {
        return creneaux;
    }
    
    public void setCreneaux(List<Creneau> creneaux) {
        this.creneaux = creneaux;
    }
}
