package com.carely.enums;

/**
 * Types d'actes techniques médicaux avec leurs coûts
 */
public enum TypeActeTechnique {
    RADIOGRAPHIE("Radiographie", 500.0),
    ECHOGRAPHIE("Échographie", 700.0),
    IRM("IRM", 3000.0),
    ELECTROCARDIOGRAMME("Électrocardiogramme", 150.0),
    LASER_DERMATOLOGIQUE("Laser (Dermatologique)", 500.0),
    FOND_OEIL("Fond d'œil", 250.0),
    ANALYSE_SANG("Analyse de sang", 180.0),
    ANALYSE_URINE("Analyse d'urine", 120.0);

    private final String libelle;
    private final double prix;

    TypeActeTechnique(String libelle, double prix) {
        this.libelle = libelle;
        this.prix = prix;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPrix() {
        return prix;
    }
}