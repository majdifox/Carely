package com.carely.entity;

import com.carely.enums.TypeActeTechnique;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entité représentant un acte technique médical
 * (Radiographie, IRM, Analyse de sang, etc.)
 */
@Entity
@Table(name = "actes_techniques")
public class ActeTechnique {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TypeActeTechnique type;
    
    @Column(nullable = false)
    private double cout;
    
    @Column(name = "date_realisation")
    private LocalDateTime dateRealisation;
    
    @Column(length = 1000)
    private String resultats; // Résultats de l'acte
    
    @Column(length = 500)
    private String observations;
    
    // Constructeurs
    public ActeTechnique() {
        this.dateRealisation = LocalDateTime.now();
    }
    
    public ActeTechnique(Consultation consultation, TypeActeTechnique type) {
        this();
        this.consultation = consultation;
        this.type = type;
        this.cout = type.getPrix();
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
    
    public TypeActeTechnique getType() {
        return type;
    }
    
    public void setType(TypeActeTechnique type) {
        this.type = type;
        if (type != null) {
            this.cout = type.getPrix();
        }
    }
    
    public double getCout() {
        return cout;
    }
    
    public void setCout(double cout) {
        this.cout = cout;
    }
    
    public LocalDateTime getDateRealisation() {
        return dateRealisation;
    }
    
    public void setDateRealisation(LocalDateTime dateRealisation) {
        this.dateRealisation = dateRealisation;
    }
    
    public String getResultats() {
        return resultats;
    }
    
    public void setResultats(String resultats) {
        this.resultats = resultats;
    }
    
    public String getObservations() {
        return observations;
    }
    
    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    @PrePersist
    protected void onCreate() {
        if (dateRealisation == null) {
            dateRealisation = LocalDateTime.now();
        }
        if (type != null && cout == 0) {
            cout = type.getPrix();
        }
    }
}
