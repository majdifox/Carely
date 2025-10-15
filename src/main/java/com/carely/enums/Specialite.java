package com.carely.enums;

/**
 * Spécialités médicales
 */
public enum Specialite {
    CARDIOLOGIE("Cardiologue"),
    PNEUMOLOGIE("Pneumologue"),
    DERMATOLOGIE("Dermatologue"),
    NEUROLOGIE("Neurologue"),
    ENDOCRINOLOGIE("Endocrinologue"),
    OPHTALMOLOGIE("Ophtalmologue"),
    ORL("ORL"),
    PEDIATRIE("Pédiatre"),
    GYNECOLOGIE("Gynécologue"),
    PSYCHIATRIE("Psychiatre"),
    RADIOLOGIE("Radiologue");

    private final String libelle;

    Specialite(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}