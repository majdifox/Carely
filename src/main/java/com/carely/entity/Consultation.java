package com.carely.entity;

import com.carely.enums.StatutConsultation;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant une consultation médicale
 * Créée par le médecin généraliste pour un patient
 */
@Entity
@Table(name = "consultations")
public class Consultation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_id", nullable = false)
    private MedecinGeneraliste medecinGeneraliste;
    
    @Column(nullable = false, length = 500)
    private String motif; // Raison de la consultation
    
    @Column(length = 2000)
    private String observations; // Observations du médecin
    
    @Column(name = "examen_clinique", length = 2000)
    private String examenClinique; // Examen physique
    
    @Column(length = 1000)
    private String symptomes; // Ce que le patient ressent
    
    @Column(length = 500)
    private String diagnostic; // Maladie identifiée
    
    @Column(length = 2000)
    private String traitement; // Prescription
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatutConsultation statut = StatutConsultation.EN_COURS;
    
    @Column(name = "cout_consultation", nullable = false)
    private double coutConsultation = 150.0; // Coût fixe
    
    @Column(name = "date_consultation", nullable = false)
    private LocalDateTime dateConsultation;
    
    @Column(name = "date_cloture")
    private LocalDateTime dateCloture;
    
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActeTechnique> actesTechniques = new ArrayList<>();
    
    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
    private DemandeExpertise demandeExpertise;
    
    // Constructeurs
    public Consultation() {
        this.dateConsultation = LocalDateTime.now();
    }
    
    public Consultation(Patient patient, MedecinGeneraliste medecin, String motif) {
        this();
        this.patient = patient;
        this.medecinGeneraliste = medecin;
        this.motif = motif;
    }
    
    // Méthodes utilitaires
    public double getCoutTotal() {
        double total = coutConsultation;
        
        // Ajouter le coût des actes techniques
        for (ActeTechnique acte : actesTechniques) {
            total += acte.getCout();
        }
        
        // Ajouter le coût de l'expertise si présente
        if (demandeExpertise != null && demandeExpertise.getMedecinSpecialiste() != null) {
            total += demandeExpertise.getMedecinSpecialiste().getTarifExpertise();
        }
        
        return total;
    }
    
    public void cloturer() {
        this.statut = StatutConsultation.TERMINEE;
        this.dateCloture = LocalDateTime.now();
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public MedecinGeneraliste getMedecinGeneraliste() {
        return medecinGeneraliste;
    }
    
    public void setMedecinGeneraliste(MedecinGeneraliste medecinGeneraliste) {
        this.medecinGeneraliste = medecinGeneraliste;
    }
    
    public String getMotif() {
        return motif;
    }
    
    public void setMotif(String motif) {
        this.motif = motif;
    }
    
    public String getObservations() {
        return observations;
    }
    
    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public String getExamenClinique() {
        return examenClinique;
    }
    
    public void setExamenClinique(String examenClinique) {
        this.examenClinique = examenClinique;
    }
    
    public String getSymptomes() {
        return symptomes;
    }
    
    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }
    
    public String getDiagnostic() {
        return diagnostic;
    }
    
    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
    
    public String getTraitement() {
        return traitement;
    }
    
    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }
    
    public StatutConsultation getStatut() {
        return statut;
    }
    
    public void setStatut(StatutConsultation statut) {
        this.statut = statut;
    }
    
    public double getCoutConsultation() {
        return coutConsultation;
    }
    
    public void setCoutConsultation(double coutConsultation) {
        this.coutConsultation = coutConsultation;
    }
    
    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }
    
    public void setDateConsultation(LocalDateTime dateConsultation) {
        this.dateConsultation = dateConsultation;
    }
    
    public LocalDateTime getDateCloture() {
        return dateCloture;
    }
    
    public void setDateCloture(LocalDateTime dateCloture) {
        this.dateCloture = dateCloture;
    }
    
    public List<ActeTechnique> getActesTechniques() {
        return actesTechniques;
    }
    
    public void setActesTechniques(List<ActeTechnique> actesTechniques) {
        this.actesTechniques = actesTechniques;
    }
    
    public DemandeExpertise getDemandeExpertise() {
        return demandeExpertise;
    }
    
    public void setDemandeExpertise(DemandeExpertise demandeExpertise) {
        this.demandeExpertise = demandeExpertise;
    }
    
    @PrePersist
    protected void onCreate() {
        if (dateConsultation == null) {
            dateConsultation = LocalDateTime.now();
        }
    }
}
