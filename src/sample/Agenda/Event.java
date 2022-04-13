package sample.Agenda;


import javafx.scene.paint.Color;

import java.time.LocalDate;

public class Event {

    /**
     * Var : Date de l'evenement
     */
    private final LocalDate date;

    /**
     * Var : Début de l'evenement
     */
    private final int startingTime;

    /**
     * Var : Fin de l'evenement
     */
    private final int endingTime;

    /**
     * Var : Nom de l'evenement
     */
    private final String nom;

    /**
     * Var : Filtre parmis Fac , Plante , Personnel
     */
    private final Filter filter;

    /**
     * Var : Nom de la plante
     */
    private final String NomPlante;

    public Event(Filter filter, LocalDate date, int startingTime, int endingTime, String nom, String NomPlante) {
        this.filter = filter;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.nom = nom;
        this.NomPlante = NomPlante;
    }

    public Event(Filter filter, LocalDate date, int startingTime, String label, String plantName) {
        this.filter = filter;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = startingTime + 1;
        this.nom = label;
        this.NomPlante = plantName;
    }

    public Color getColor() {
        return filter.getColor();
    }

    public LocalDate getDay() {
        return date;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public int getEndingTime() {
        return endingTime;
    }

    public String getNom() {
        return nom;
    }

    public Filter getFilter() {
        return filter;
    }

    public String getNomPlante() {
        return NomPlante;
    }
}