package sample.Agenda;

import javafx.scene.paint.Color;


public class Filter {
    /**
     * Var : Nom du filtre
     */
    private final String Nom;

    /**
     * Var : Couleur du filtre
     */
    private final Color couleur;

    /**
     * Var : Si le filtre est coche ou non
     */
    private boolean estCoche;

    //Constructeur de la classe
    public Filter(String Nom, Color couleur) {
        this.Nom = Nom;
        this.couleur = couleur;
        this.estCoche = true;
    }

    /**
     * Recupere et retourne le nom du filtre
     * @return Nom du filtre
     */
    public String getNom() {
        return Nom;
    }

    /**
     * Recupere et retourne la couleur du filtre
     * @return Couleur du filtre
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Decoche le filtre
     */
    public void unTick() {
        estCoche = false;
    }

    /**
     * Coche le filtre
     */
    public void tick() {
        estCoche = true;
    }

    /**
     * Recupere et retourne si le filtre est coche ou non
     * @return True ou False en fonction si le filtre est coche
     */
    public boolean isEstCoche() {
        return estCoche;
    }

}
