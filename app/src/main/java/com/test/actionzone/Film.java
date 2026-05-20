package com.test.actionzone;

public class Film {

    // Données venant de TMDB
    String titre;
    String synopsis;
    String affiche;
    double note;

    // Données personnelles de l'utilisateur
    String statut;
    float maNote;
    String avis;

    // Constructeur avec les données TMDB
    public Film(String titre, String synopsis, String affiche, double note) {
        this.titre = titre;
        this.synopsis = synopsis;
        this.affiche = affiche;
        this.note = note;

        // Valeurs par défaut pour les données personnelles
        this.statut = "A voir";
        this.maNote = 0;
        this.avis = "";
    }
}