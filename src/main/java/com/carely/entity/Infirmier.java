package com.carely.entity;

import com.carely.enums.Role;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un infirmier
 * Responsable de l'accueil et l'enregistrement des patients
 */
@Entity
@Table(name = "infirmiers")
public class Infirmier extends Utilisateur {
    
    @Column(name = "numero_badge", unique = true, length = 20)
    private String numeroBadge;
    
    @Column(length = 100)
    private String service; // Service hospitalier affecté
    
    @OneToMany(mappedBy = "infirmier", cascade = CascadeType.ALL)
    private List<Patient> patientsEnregistres = new ArrayList<>();
    
    // Constructeurs
    public Infirmier() {
        super();
        setRole(Role.INFIRMIER);
    }
    
    public Infirmier(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, Role.INFIRMIER);
    }
    
    // Getters et Setters
    public String getNumeroBadge() {
        return numeroBadge;
    }
    
    public void setNumeroBadge(String numeroBadge) {
        this.numeroBadge = numeroBadge;
    }
    
    public String getService() {
        return service;
    }
    
    public void setService(String service) {
        this.service = service;
    }
    
    public List<Patient> getPatientsEnregistres() {
        return patientsEnregistres;
    }
    
    public void setPatientsEnregistres(List<Patient> patientsEnregistres) {
        this.patientsEnregistres = patientsEnregistres;
    }
}
