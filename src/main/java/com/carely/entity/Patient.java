package com.carely.entity;

import com.carely.enums.StatutPatient;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un patient
 */
@Entity
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nom;
    
    @Column(nullable = false, length = 100)
    private String prenom;
    
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;
    
    @Column(name = "numero_securite_sociale", unique = true, nullable = false, length = 20)
    private String numeroSecuriteSociale;
    
    @Column(length = 20)
    private String telephone;
    
    @Column(length = 200)
    private String adresse;
    
    @Column(length = 100)
    private String mutuelle;
    
    @Column(length = 500)
    private String antecedents; // Antécédents médicaux
    
    @Column(length = 500)
    private String allergies;
    
    @Column(name = "traitements_en_cours", length = 500)
    private String traitementsEnCours;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutPatient statut = StatutPatient.EN_ATTENTE;
    
    @Column(name = "date_enregistrement", nullable = false)
    private LocalDateTime dateEnregistrement;
    
    @Column(name = "heure_arrivee", nullable = false)
    private LocalDateTime heureArrivee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infirmier_id")
    private Infirmier infirmier;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignesVitaux> signesVitaux = new ArrayList<>();
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Consultation> consultations = new ArrayList<>();
    
    // Constructeurs
    public Patient() {
        this.dateEnregistrement = LocalDateTime.now();
        this.heureArrivee = LocalDateTime.now();
    }
    
    public Patient(String nom, String prenom, LocalDate dateNaissance, String numeroSecuriteSociale) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
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
    
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }
    
    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getMutuelle() {
        return mutuelle;
    }
    
    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }
    
    public String getAntecedents() {
        return antecedents;
    }
    
    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }
    
    public String getAllergies() {
        return allergies;
    }
    
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    
    public String getTraitementsEnCours() {
        return traitementsEnCours;
    }
    
    public void setTraitementsEnCours(String traitementsEnCours) {
        this.traitementsEnCours = traitementsEnCours;
    }
    
    public StatutPatient getStatut() {
        return statut;
    }
    
    public void setStatut(StatutPatient statut) {
        this.statut = statut;
    }
    
    public LocalDateTime getDateEnregistrement() {
        return dateEnregistrement;
    }
    
    public void setDateEnregistrement(LocalDateTime dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }
    
    public LocalDateTime getHeureArrivee() {
        return heureArrivee;
    }
    
    public void setHeureArrivee(LocalDateTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }
    
    public Infirmier getInfirmier() {
        return infirmier;
    }
    
    public void setInfirmier(Infirmier infirmier) {
        this.infirmier = infirmier;
    }
    
    public List<SignesVitaux> getSignesVitaux() {
        return signesVitaux;
    }
    
    public void setSignesVitaux(List<SignesVitaux> signesVitaux) {
        this.signesVitaux = signesVitaux;
    }
    
    public List<Consultation> getConsultations() {
        return consultations;
    }
    
    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
    
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    @PrePersist
    protected void onCreate() {
        if (dateEnregistrement == null) {
            dateEnregistrement = LocalDateTime.now();
        }
        if (heureArrivee == null) {
            heureArrivee = LocalDateTime.now();
        }
    }
}
