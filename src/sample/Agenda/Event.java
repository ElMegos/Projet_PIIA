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
    private final int debutEvent;

    /**
     * Var : Fin de l'evenement
     */
    private final int finEvent;

    /**
     * Var : Nom de l'evenement
     */
    private final String nom;

    /**
     * Var : Filtre parmis Fac , Plante , Personnel
     */
    private final Filter filtre;

    /**
     * Var : Nom de la plante
     */
    private final String NomPlante;

    //Constructeur de la classe
    public Event(Filter filtre, LocalDate date, int debutEvent, int finEvent, String nom, String NomPlante) {

        this.date = date;
        this.debutEvent = debutEvent;
        this.finEvent = finEvent;
        this.nom = nom;
        this.filtre = filtre;
        this.NomPlante = NomPlante;

    }

    /**
     * RecupereReturn la couleur du filtre
     * @return Color du filtre
     */
    public Color getColor() {
        return filtre.getCouleur();
    }

    /**
     * Recupere et retourne la date de l'evenement
     * @return Date de l'evenement
     */
    public LocalDate getDay() {
        return date;
    }

    /**
     * Recupere et retourne la date du debut de l'evenement
     * @return Date du debut de l'evenement
     */
    public int getDebutEvent() {
        return debutEvent;
    }

    /**
     * Recupere et retourne la date de la fin de l'evenement
     * @return Date de la fin de l'evenement
     */
    public int getFinEvent() {
        return finEvent;
    }

    /**
     * Recupere et retourne le nom de l'evenement
     * @return Nom de l'evenement
     */
    public String getNom() {
        return nom;
    }

    /**
     * Recupere et retourne le filtre de l'evenement
     * @return Filtre de l'evenement
     */
    public Filter getFiltre() {
        return filtre;
    }

    /**
     * Recupere et retourne le nom de plante
     * @return Nom de la plante
     */
    public String getNomPlante() {
        return NomPlante;
    }
}