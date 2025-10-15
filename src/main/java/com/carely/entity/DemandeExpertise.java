package com.carely.entity;

import com.carely.enums.Priorite;
import com.carely.enums.Specialite;
import com.carely.enums.StatutExpertise;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entité représentant une demande de télé-expertise
 * Créée par un médecin généraliste pour obtenir l'avis d'un spécialiste
 */
@Entity
@Table(name = "demandes_expertise")
public class DemandeExpertise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_demandeur_id", nullable = false)
    private MedecinGeneraliste medecinDemandeur;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecin_specialiste_id")
    private MedecinSpecialiste medecinSpecialiste;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creneau_id")
    private Creneau creneau;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Specialite specialiteRequise;
    
    @Column(nullable = false, length = 2000)
    private String question; // Question posée au spécialiste
    
    @Column(name = "donnees_cliniques", length = 2000)
    private String donneesCliniques; // Données et analyses fournies
    
    @Column(name = "avis_specialiste", length = 2000)
    private String avisSpecialiste; // Réponse du spécialiste
    
    @Column(length = 2000)
    private String recommandations; // Recommandations du spécialiste
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Priorite priorite = Priorite.NORMALE;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutExpertise statut = StatutExpertise.EN_ATTENTE;
    
    @Column(name = "date_demande", nullable = false)
    private LocalDateTime dateDemande;
    
    @Column(name = "date_reponse")
    private LocalDateTime dateReponse;
    
    // Constructeurs
    public DemandeExpertise() {
        this.dateDemande = LocalDateTime.now();
    }
    
    public DemandeExpertise(Consultation consultation, MedecinGeneraliste medecin, 
                           Specialite specialite, String question, Priorite priorite) {
        this();
        this.consultation = consultation;
        this.medecinDemandeur = medecin;
        this.specialiteRequise = specialite;
        this.question = question;
        this.priorite = priorite;
    }
    
    // Méthode pour terminer l'expertise
    public void terminer(String avis, String recommandations) {
        this.avisSpecialiste = avis;
        this.recommandations = recommandations;
        this.statut = StatutExpertise.TERMINEE;
        this.dateReponse = LocalDateTime.now();
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Consultation getConsultation() {
        return consultation;
    }
    
    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    
    public MedecinGeneraliste getMedecinDemandeur() {
        return medecinDemandeur;
    }
    
    public void setMedecinDemandeur(MedecinGeneraliste medecinDemandeur) {
        this.medecinDemandeur = medecinDemandeur;
    }
    
    public MedecinSpecialiste getMedecinSpecialiste() {
        return medecinSpecialiste;
    }
    
    public void setMedecinSpecialiste(MedecinSpecialiste medecinSpecialiste) {
        this.medecinSpecialiste = medecinSpecialiste;
    }
    
    public Creneau getCreneau() {
        return creneau;
    }
    
    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
    
    public Specialite getSpecialiteRequise() {
        return specialiteRequise;
    }
    
    public void setSpecialiteRequise(Specialite specialiteRequise) {
        this.specialiteRequise = specialiteRequise;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getDonneesCliniques() {
        return donneesCliniques;
    }
    
    public void setDonneesCliniques(String donneesCliniques) {
        this.donneesCliniques = donneesCliniques;
    }
    
    public String getAvisSpecialiste() {
        return avisSpecialiste;
    }
    
    public void setAvisSpecialiste(String avisSpecialiste) {
        this.avisSpecialiste = avisSpecialiste;
    }
    
    public String getRecommandations() {
        return recommandations;
    }
    
    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }
    
    public Priorite getPriorite() {
        return priorite;
    }
    
    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }
    
    public StatutExpertise getStatut() {
        return statut;
    }
    
    public void setStatut(StatutExpertise statut) {
        this.statut = statut;
    }
    
    public LocalDateTime getDateDemande() {
        return dateDemande;
    }
    
    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }
    
    public LocalDateTime getDateReponse() {
        return dateReponse;
    }
    
    public void setDateReponse(LocalDateTime dateReponse) {
        this.dateReponse = dateReponse;
    }
    
    @PrePersist
    protected void onCreate() {
        if (dateDemande == null) {
            dateDemande = LocalDateTime.now();
        }
    }
}
