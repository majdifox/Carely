package com.carely.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entité représentant un créneau horaire d'un médecin spécialiste
 * Créneaux fixes de 30 minutes
 */
@Entity
@Table(name = "creneaux", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"medecin_specialiste_id", "date_creneau", "heure_debut"})
})
public class Creneau {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_specialiste_id", nullable = false)
    private MedecinSpecialiste medecinSpecialiste;
    
    @Column(name = "date_creneau", nullable = false)
    private LocalDate dateCreneau;
    
    @Column(name = "heure_debut", nullable = false)
    private LocalTime heureDebut;
    
    @Column(name = "heure_fin", nullable = false)
    private LocalTime heureFin;
    
    @Column(nullable = false)
    private boolean disponible = true;
    
    @OneToOne(mappedBy = "creneau")
    private DemandeExpertise demandeExpertise;
    
    // Constructeurs
    public Creneau() {
    }
    
    public Creneau(MedecinSpecialiste medecin, LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
        this.medecinSpecialiste = medecin;
        this.dateCreneau = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }
    
    // Méthodes utilitaires
    public boolean estPasse() {
        LocalDate aujourd = LocalDate.now();
        if (dateCreneau.isBefore(aujourd)) {
            return true;
        }
        if (dateCreneau.isEqual(aujourd)) {
            return heureDebut.isBefore(LocalTime.now());
        }
        return false;
    }
    
    public boolean estDisponible() {
        return disponible && !estPasse();
    }
    
    public void reserver() {
        this.disponible = false;
    }
    
    public void liberer() {
        this.disponible = true;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public MedecinSpecialiste getMedecinSpecialiste() {
        return medecinSpecialiste;
    }
    
    public void setMedecinSpecialiste(MedecinSpecialiste medecinSpecialiste) {
        this.medecinSpecialiste = medecinSpecialiste;
    }
    
    public LocalDate getDateCreneau() {
        return dateCreneau;
    }
    
    public void setDateCreneau(LocalDate dateCreneau) {
        this.dateCreneau = dateCreneau;
    }
    
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    
    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public LocalTime getHeureFin() {
        return heureFin;
    }
    
    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public DemandeExpertise getDemandeExpertise() {
        return demandeExpertise;
    }
    
    public void setDemandeExpertise(DemandeExpertise demandeExpertise) {
        this.demandeExpertise = demandeExpertise;
    }
    
    @Override
    public String toString() {
        return dateCreneau + " " + heureDebut + " - " + heureFin;
    }
}
