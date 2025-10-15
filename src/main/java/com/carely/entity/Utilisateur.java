package com.carely.entity;

import com.carely.enums.Role;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe abstraite représentant un utilisateur du système
 * Parent pour Infirmier, MedecinGeneraliste et MedecinSpecialiste
 */
@Entity
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nom;
    
    @Column(nullable = false, length = 100)
    private String prenom;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false)
    private String motDePasse; // Hashé avec BCrypt
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;
    
    @Column(length = 20)
    private String telephone;
    
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;
    
    @Column(name = "derniere_connexion")
    private LocalDateTime derniereConnexion;
    
    @Column(nullable = false)
    private boolean actif = true;
    
    // Constructeurs
    public Utilisateur() {
        this.dateCreation = LocalDateTime.now();
    }
    
    public Utilisateur(String nom, String prenom, String email, String motDePasse, Role role) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public LocalDateTime getDerniereConnexion() {
        return derniereConnexion;
    }
    
    public void setDerniereConnexion(LocalDateTime derniereConnexion) {
        this.derniereConnexion = derniereConnexion;
    }
    
    public boolean isActif() {
        return actif;
    }
    
    public void setActif(boolean actif) {
        this.actif = actif;
    }
    
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}
