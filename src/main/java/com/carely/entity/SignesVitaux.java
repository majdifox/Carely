package com.carely.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entité représentant les signes vitaux d'un patient
 * Mesurés par l'infirmier lors de l'accueil
 */
@Entity
@Table(name = "signes_vitaux")
public class SignesVitaux {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @Column(name = "tension_arterielle", length = 20)
    private String tensionArterielle; // Ex: "120/80"
    
    @Column(name = "frequence_cardiaque")
    private Integer frequenceCardiaque; // En battements par minute
    
    @Column(name = "temperature")
    private Double temperature; // En degrés Celsius
    
    @Column(name = "frequence_respiratoire")
    private Integer frequenceRespiratoire; // En respirations par minute
    
    @Column(name = "poids")
    private Double poids; // En kg
    
    @Column(name = "taille")
    private Double taille; // En cm
    
    @Column(name = "date_mesure", nullable = false)
    private LocalDateTime dateMesure;
    
    @Column(length = 500)
    private String observations;
    
    // Constructeurs
    public SignesVitaux() {
        this.dateMesure = LocalDateTime.now();
    }
    
    public SignesVitaux(Patient patient) {
        this();
        this.patient = patient;
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
    
    public String getTensionArterielle() {
        return tensionArterielle;
    }
    
    public void setTensionArterielle(String tensionArterielle) {
        this.tensionArterielle = tensionArterielle;
    }
    
    public Integer getFrequenceCardiaque() {
        return frequenceCardiaque;
    }
    
    public void setFrequenceCardiaque(Integer frequenceCardiaque) {
        this.frequenceCardiaque = frequenceCardiaque;
    }
    
    public Double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    
    public Integer getFrequenceRespiratoire() {
        return frequenceRespiratoire;
    }
    
    public void setFrequenceRespiratoire(Integer frequenceRespiratoire) {
        this.frequenceRespiratoire = frequenceRespiratoire;
    }
    
    public Double getPoids() {
        return poids;
    }
    
    public void setPoids(Double poids) {
        this.poids = poids;
    }
    
    public Double getTaille() {
        return taille;
    }
    
    public void setTaille(Double taille) {
        this.taille = taille;
    }
    
    public LocalDateTime getDateMesure() {
        return dateMesure;
    }
    
    public void setDateMesure(LocalDateTime dateMesure) {
        this.dateMesure = dateMesure;
    }
    
    public String getObservations() {
        return observations;
    }
    
    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    @PrePersist
    protected void onCreate() {
        if (dateMesure == null) {
            dateMesure = LocalDateTime.now();
        }
    }
}
